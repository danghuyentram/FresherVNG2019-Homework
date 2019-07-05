#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>



#define PORT 4444
#define MAX 1000
#define MAXCLIENT 10
#define SA struct sockaddr 
int arr[MAX];

int rankarr[MAXCLIENT];
int length=MAX;
pthread_mutex_t mutex;

struct clientInfo{
	int rank;
	int socket;
	int sum;
	pthread_t thread;
	
} clientInfo;

struct clientInfo clientArr[10];
int numberClient=0;
int sockfd;

int sumt=0;
char filenameClientLog[40];




// init client
void initClient(void *newAddr,int index){
	clientArr[index].rank=0;
	clientArr[index].socket=newAddr;
	clientArr[index].sum=0;
	
}

// calculate sum by string client send
int sum(int i){
    char filename[40]="serverLog/client";
    char buffer[10];
	bzero(buffer, 10); 
	sprintf(buffer, "%d", clientArr[i].socket);
	strcat(filename,buffer);
	strcat(filename,".txt");

    FILE *fo=fopen(filename,"r+");
    bzero(buffer,10);

    if(!fo)
		printf("Cannot open file %s\n",filename);
	else{
		while (fgets(buffer, 10, fo) != NULL)
        {
            int t=atoi(buffer);
            
            clientArr[i].sum+=t;
            bzero(buffer,10);
        }

        fclose(fo);
	}
    return clientArr[i].sum;
    
}

// ranking for client result
void rank(){
	for(int i=0;i<numberClient-1;i++)
		for(int j=i+1;j<numberClient;j++)
			if(clientArr[i].sum<clientArr[j].sum){
				struct clientInfo tmp=clientArr[i];
				clientArr[i]=clientArr[j];
                clientArr[j]=tmp;
			}
}


// send ball to client when client request
void ServerSendBallToClient(int newsock){
	char buffer[1024];
	int number;
	
	while(1)
	{
	
		//sleep(1);
			bzero(buffer,1024);
			read(newsock,buffer,1024);
				
			if(strcmp(buffer,"Send me a number\n")==0) // client request server send ball
			{
				pthread_mutex_lock(&mutex);
				if(length>=0){ // arr still have ball
					number=arr[length-1];
					arr[length-1]=0;
					length--;					
					bzero(buffer,1024);
					sprintf(buffer, "%d", number);
					printf("Send to client %d %s\n",newsock,buffer);
					write(newsock,buffer,1024);		
					
				}
				else{ // arr not have ball										
					break;
				}	
				pthread_mutex_unlock(&mutex);	
				
			}
	}
}

// create filename client 
void createFileNameClient(int i){

    bzero(filenameClientLog,40);
    strcat(filenameClientLog,"serverLog/client");
	char buffer[10];
	bzero(buffer, 10); 
	sprintf(buffer, "%d", clientArr[i].socket);
	strcat(filenameClientLog,buffer);
	strcat(filenameClientLog,".txt");

}

// write file result ball from client
void writeFileClientBall(int i,char *buff){

    createFileNameClient(i);
	FILE *fo=fopen(filenameClientLog,"a+");
	if(!fo)
		printf("Cannot open file %s\n",filenameClientLog);
	else{
		fprintf(fo,"%s",buff);
        fclose(fo);

	}
    
}

// close all socket
void closeAllSocket(){
	for(int i=0;i<numberClient;i++)
		close(clientArr[i].socket);

	close(sockfd);	
}


// send rank to client
void sendRankResultToClient(){
	FILE *fo=fopen("serverLog/rankResult.txt","r+");
	
	char buff[1024];
	char tmp[1024];
	int t=0,k=0;

    if(!fo)
		printf("Cannot open file rankResult.txt\n");
	else{
        
		while(!feof(fo) && t<numberClient){
		
		 fgets(tmp, 1024, (FILE*)fo);
		 strcat(buff,tmp);
		 strcat(buff,"\n");
		 t++;
        }

        for(int i=0;i<numberClient;i++){
            write(clientArr[i].socket,buff,1024);
        }

        fclose(fo);

	}

    closeAllSocket();
	
}

// refreshfile
void refreshFile(int i){

    createFileNameClient(i);
    FILE *fo=fopen(filenameClientLog,"w+");

    if(!fo)
		printf("Cannot open file %s\n",filenameClientLog);
	else{
        
		fprintf(fo,"%d",0);
        fclose(fo);
	}

}


// when server not have ball, server request client to send result
void ClientSendResultToServer(){
	char buffer[1024];
	char filename[11]="client";

	for(int i=0;i<numberClient;i++)
	{
            // numloop is number of block 1024 of file client send
            int numloop;
			// send mess end of ball to every client
			bzero(buffer,1024);
			strcat(buffer,"End of ball");
			write(clientArr[i].socket,buffer,1024);

			// read mess from client until recv a result
			bzero(buffer,1024);
			read(clientArr[i].socket,buffer,1024);

			while(strcmp(buffer,"Send me a number\n")==0){
				bzero(buffer,1024);
				strcat(buffer,"End of ball");
				write(clientArr[i].socket,buffer,1024);

                // recv numloop from client
				bzero(buffer,1024);
				read(clientArr[i].socket,buffer,1024);

			};
            
            // recv file from client           
            numloop=atoi(buffer);
            bzero(buffer,1024);

            refreshFile(i);
            for(int j=0;j<numloop;j++){
                read(clientArr[i].socket,buffer,1024);
                writeFileClientBall(i,buffer);
                bzero(buffer,1024);
            }

            // calculate sum
           int sumc=sum(i);
           


	}


	// ranking and write log ranking on server
	rank();
	FILE *fo=fopen("serverLog/rankResult.txt","w+");

	for(int i=0;i<numberClient;i++){
		fprintf(fo,"Client %d rank %d sum %d\n",clientArr[i].socket,i+1,clientArr[i].sum);
        printf("\nClient %d rank %d sum %d\n",clientArr[i].socket,i+1,clientArr[i].sum);

    }
	fprintf(fo,"\0");
	fclose(fo);

	// send ranking to client
	sendRankResultToClient();

}


void *sendBall(void *arg){
	int newsock = arg;
		for(int i=0;i<length;i++){
		printf("%d ",arr[i]);
	}

	ServerSendBallToClient(newsock);
	ClientSendResultToServer();

	close(sockfd);
    
    exit(0);

	
}


int main(){


    srand(time(NULL));
	for(int i=0;i<length;i++){
	arr[i] = (rand() %   (10 - 100 + 1)) + 10; 
	//	arr[i]=i+1;
		printf("%d ",arr[i]);
        sumt+=arr[i];		
	}

    printf("sum all %d\n",sumt);

	int ret;
	 struct sockaddr_in serverAddr;

	int newSocket;
	struct sockaddr_in newAddr;

	socklen_t addr_size;

	char buffer[1024];
    char buffnumber[1024];
	
	// input number client
	printf("\nEnter number client:");
	scanf("%d",&numberClient);


    // set up socket : domain AF_INET : IPv4, type SOCK_STREAM: TCP, protocol: 0
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0){
		printf("[-]Error in connection.\n");
		exit(1);
	}
	printf("[+]Server Socket is created.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr)); // zero structure out
	serverAddr.sin_family = AF_INET; // match the socket() call
	serverAddr.sin_port = htons(PORT);  // bind to any local address, htons: host to network short/long
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");   // specify port to listen on


    // binding
    // sockfd: file descriptor socket() returned
    // serverAddr: struck sockaddr_in for IPv4
    // cast (struct sockaddr_in*) to (struct sockaddr*)

	ret = bind(sockfd, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("[-]Error in binding.\n");
		exit(1);
	}
	printf("[+]Bind to port %d\n", 4444);


    // listen
    // sockfd: file descriptor socket() returned, backlog: number of peding connection to queue
	if(listen(sockfd, 9) == 0){ // listen các incoming connection
		printf("[+]Listening....\n");
	}else{
		printf("[-]Error in binding.\n");
	}


	// accept
	printf("Waiting for %d client connect:\n",numberClient);
	int countNumberCli=0;
	while(countNumberCli<numberClient){
		newSocket = accept(sockfd, (struct sockaddr*)&newAddr, &addr_size);
		if(newSocket<0){
			exit(1);
		}
		initClient(newSocket,countNumberCli);
		printf("Connection accepted from %s:%d %d %d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port),countNumberCli,numberClient);
		countNumberCli++;

        // send socketid to client
        char tmp[2];
        bzero(tmp,2);
        sprintf(tmp,"%d",newSocket);
        write(newSocket,tmp,2);

	}


	// create thread for each socket
	for(int i=0;i<numberClient;i++)
		{
			if(pthread_create(&clientArr[i].thread,NULL,sendBall,(void*)clientArr[i].socket)!=0){
				printf("Error to create thread\n");
				exit(0);
			}
			printf("Client: %d \n",clientArr[i].socket);
						
		}


	for(int i=0;i<numberClient;i++){
			void * status;
			if(pthread_join(clientArr[i].thread,&status)!=0){
				printf("Error to join thread\n");
				exit(0);
			}		
	}

 
	return 0;
}
