#include <stdio.h>
#include <time.h>
struct tm tm;   
time_t start,enxd;
double sec;
int counter;
int main(){
    int switchCounter = 0;
    int checkSwitch;

    checkSwitch = 1; // I put this in purpose since I have no idea how you're going to read the switch. 
                     // Thus, this assumes the switch is always closed.

    while(switchCounter != 100){ 
        // 1. Wait for 10 seconds
        sec = 0;
        time(&start);

        while(sec !=10){
            ++counter;
            time(&enxd);
            sec=difftime(enxd,start);
        }

        // 2. Read the state of the switch here.
        // ..............

        // 3. Simple if-else
        if (checkSwitch == 1){ //switch is closed
            switchCounter++;
            printf("Counter incremented. Current = %i \n", switchCounter);
        }
        else //if switch is open
        {
            switchCounter = 0 ;// Iam confused here, is this what you want ?
            printf("Switch is open \n");
        }
    }
    // 4. Finally when your counter reaches 100, you wanna do something here
    // ............................

    return 0;
}

