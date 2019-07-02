#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#define PORT 4444



#define MAX 1024 

#define SA struct sockaddr 

int length=0;
int arr[1000];
char result[MAX];
int clientSocket,ret;
char sockid[2];
char fileClientLogName[40];




int cmpfunc (const void * a, const void * b) {
   return ( *(int*)a - *(int*)b );
}

// change arr to string result ball
void generateResult(){
	char buffer[1024];
	qsort(arr,length, sizeof(int), cmpfunc);
	bzero(result,MAX);

	// write arr sorted again to file
	FILE *fo=fopen(fileClientLogName,"w+");
	
				
	for(int j=0;j<length;j++)
	{
			fprintf(fo,"%d\n",arr[j]);
			bzero(buffer,MAX);
			sprintf(buffer, "%d", arr[j]);					
			strcat(result,buffer);
			strcat(result,"\n");
			
	}
	fclose(fo);


}

void sendFile(){

	FILE *fo=fopen(fileClientLogName,"rb");

	// calculate size of file log
	fseek(fo,0,SEEK_END);
	int sizeFile=(int)ftell(fo);
	fseek(fo,0,SEEK_SET);

	// calculate number of loop to send
	int numloop=0;
	if((sizeFile % 1024)==0){
		numloop=sizeFile/1024;
	}
	else numloop=sizeFile/1024 +1;

	// send numloop to server
	char num[10];
	bzero(num,10);
	sprintf(num,"%d",numloop);

	write(clientSocket,num,10);

	// send file to server
	int n;
	char tmp[1024];
	bzero(tmp,1024);

	for(int i=0;i<numloop;i++){
		n=fread(tmp,sizeof(char),1024,fo);
		write(clientSocket,tmp,n);
		bzero(tmp,1024);
	}

	fclose(fo);
}

// send result string to server
void sendResultToServer(int socket){
	char buffer[1024];

	generateResult();
	bzero(buffer,1024);

	printf("Result: %s\n",result);
	sendFile();

	//write(socket,result,1024);


}

// write file rank on client
void writeFileRank(char *buffer,int socket){

	char filename[40]="clientLog/rankResultOfClient";
	//bzero(filename,40);
	strcat(filename,sockid);
	strcat(filename,".txt");

	FILE *fo=fopen(filename,"w+");

	fprintf(fo,"%s",buffer);
	fprintf(fo,"\0");

	fclose(fo);
}


// request server send a rank result
void recvRankResult(int socket){
	char buffer[1024];
	do{
		bzero(buffer,1024);
		read(socket,buffer,1024);
	}while(strcmp(buffer,"End of ball")==0);
	
	writeFileRank(buffer,socket);
	printf("Rank\n%s\n",buffer);	

}




int main(){

	
	struct sockaddr_in serverAddr;
	char buffer[1024];
    char buffnumber[1024];

	clientSocket = socket(AF_INET, SOCK_STREAM, 0);
	if(clientSocket < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Client Socket is created.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(PORT);
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

    // connect to server
	ret = connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Connected to Server\n");

    
	// recv sockid from server
	read(clientSocket,sockid,sizeof(sockid));
	printf("sock id %s\n",sockid);

	// create log file on client
	bzero(fileClientLogName,40);
	strcat(fileClientLogName,"clientLog/client");
	strcat(fileClientLogName,sockid);
	strcat(fileClientLogName,".txt");
	FILE *fo=fopen(fileClientLogName,"a+");
	

	bzero(buffer, MAX); 
	while(1){

        // send mess to server to require receive a number
		
        char mes[18]="Send me a number\n";
		write(clientSocket,mes,18);
		bzero(buffer,1024);

		if(read(clientSocket,buffer,1024)<0){
			printf("Error to receiving data from server\n");
		}else
		{
			printf("Receive from server: %s\n",buffer);
			if(strcmp(buffer,"End of ball")==0){
				fclose(fo);
				sendResultToServer(clientSocket);
				break;					
			}
			else{
				
				int k =atoi(buffer);
				fprintf(fo,"%d\n",k);
				arr[length]=k;   
				length++; 
			}
			
		}
	}

	recvRankResult(clientSocket);


	return 0;
}
