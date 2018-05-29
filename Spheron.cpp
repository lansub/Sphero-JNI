#include<stdio.h>
#include<jni.h>
#include "Spheron.h"
#include <stddef.h>
#include <unistd.h>
#include <iostream>
#include "bluetooth/bluez_adaptor.h"
#include "Sphero.hpp"
#include "packets/Constants.hpp"
//#include "Spheron.hpp"

using namespace std;

string address = "CD:72:26:BA:E7:32";

Sphero* sph = new Sphero(address.c_str(), new bluez_adaptor());

uint32_t sensorArr[] = {
		mask::RAW_ACCEL_X,
		mask::RAW_ACCEL_X | mask::RAW_GYRO_X,
		mask::RAW_ACCEL_X | mask::RAW_GYRO_X | mask::RAW_RIGHT_MOTOR_BACK_EMF,
		mask::RAW_ACCEL_X | mask::RAW_GYRO_X | mask::RAW_RIGHT_MOTOR_BACK_EMF | mask::FILTERED_RIGHT_MOTOR_BACK_EMF

	};

int freq = 20;


JNIEXPORT void JNICALL Java_Spheron_test
  (JNIEnv *, jobject){
                     cout << "Hell ye, i am alive" << endl;
                     return;
}

JNIEXPORT void JNICALL Java_Spheron_mconnect
  (JNIEnv *, jobject){

 
        
        FILE* pFile = fopen("StreamSensorsWithoutSending.txt", "a");
       
	      

        //string address = "CD:72:26:BA:E7:32";
        //string address = addr;

        //Sphero* sph = new Sphero(address.c_str());
        DataBuffer* sensors = sph->getDataBuffer();

        sph->onConnect([pFile]() {
		cout << "Oh crap! I am here again" << endl;
		});


         if (sph->connect())
          {
            cout << "Connected to Sphero" << endl;
          }
            else
          {
             cout << "Could not connect to Sphero" << endl;
             //return -1;
          }
          
      
        return;
} 

JNIEXPORT void JNICALL Java_Spheron_row
  (JNIEnv *, jobject){
            sph->setColor(255, 0, 0);

            while(1){
            //sph->setRawMotor(1, 255, 2, 255);
              sph->roll(200,50,1);
            usleep(100000);
          }
          

        return;

    }


JNIEXPORT void JNICALL Java_Spheron_rolltopos
  (JNIEnv *env, jobject thisObj, jint x, jint y){


   uint16_t a = (uint16_t)x;
   uint16_t b = (uint16_t)y;

   sph -> rollToPosition(a, b, 90);
}
      

