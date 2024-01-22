#include <Servo.h>
int retardo=2;          // Tiempo de retardo en milisegundos (Velocidad del Motor)

int numero_pasos = 200;   // Valor en grados donde se encuentra el motor

Servo servo; //Creamos un objeto Servo de nombre... servo
void setup() {                
Serial.begin(9600);     // inicializamos el puerto serie a 9600 baudios
servo.attach(13);       //servo al pin 13
pinMode(11, OUTPUT);    // Pin 11 conectar a IN4
pinMode(10, OUTPUT);    // Pin 10 conectar a IN3
pinMode(9, OUTPUT);     // Pin 9 conectar a IN2
pinMode(8, OUTPUT);     // Pin 8 conectar a IN1

pinMode(7, OUTPUT);    // Pin 7 conectar a IN4
pinMode(6, OUTPUT);    // Pin 6 conectar a IN3
pinMode(5, OUTPUT);     // Pin 5 conectar a IN2
pinMode(4, OUTPUT);     // Pin 4 conectar a IN1



}

void loop() {
  
  String datOp;
  int dato_rx=200;            // valor recibido en grados
  String leeCadena;       // Almacena la cadena de datos recibida
  int numero_pasos = 0;   // Valor en grados donde se encuentra el motor
  
  while (Serial.available()>0) {    // Leer el valor enviado por el Puerto serial
    delay(retardo);
    datOp = Serial.readString();
    leeCadena += datOp;     
  }  
  if (leeCadena.length()>0){  
        delay(retardo);     
        dato_rx = (dato_rx * 1.4222222222); // Ajuste de 512 vueltas a los 360 grados
  } 
  /*if(datOp=="Z"){
  servo.write(50);
  }
  else if(datOp=="X"){
  servo.write(90);
  }*/
//////////////////////////////TRAYECTORIAS CON ESCRIBIR///////////////////////////////////////////////////////////////////////////   
        if(datOp=="A"){
          servo.write(50);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         A();
         numero_pasos = numero_pasos + 1;
         }
        }
        
   else if(datOp=="D"){
        servo.write(50);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         D();
         numero_pasos = numero_pasos + 1;
        }
        }
        
  else if(datOp=="W"){
        servo.write(50);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         W();
         numero_pasos = numero_pasos + 1;
        }
        }
        
  else if(datOp=="S"){
        servo.write(50);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         S();
         numero_pasos = numero_pasos + 1;
        }
        }
//////////////////////////////TRAYECTORIAS SIN ESCRIBIR///////////////////////////////////////////////////////////////////////////
   else if(datOp=="F"){//AA es F
          servo.write(90);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         A();
         numero_pasos = numero_pasos + 1;
         }
        }
        
   else if(datOp=="G"){//DD es G
        servo.write(90);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         D();
         numero_pasos = numero_pasos + 1;
        }
        }
        
  else if(datOp=="Y"){//WW es y
        servo.write(90);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         W();
         numero_pasos = numero_pasos + 1;
        }
        }
        
  else if(datOp=="H"){//SS es H
        servo.write(90);
          while (dato_rx>numero_pasos){   // Girohacia la izquierda en grados
         S();
         numero_pasos = numero_pasos + 1;
        }
        }
//////////////////////////////TRAYECTORIAS YA HECHAS///////////////////////////////////////////////////////////////////////////
        
else if(datOp=="C"){//CUADRADO
        servo.write(50);
         C();
        }
else if(datOp=="V"){//ESCALERA
        servo.write(50);
         V();
        }
else if(datOp=="B"){//CREEPER
        //servo.write(50);
         B();
        }
//////////////////////////////APAGADO//////////////////////////////////////////////////////////////////////////
        
  leeCadena = "";   // Inicializamos la cadena de caracteres recibidos 
  apagado();         // Apagado del Motor para que no se caliente
}  ///////////////////// Fin del Loop ///////////////////////////

void A(){         // Pasos a la derecha
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, HIGH);  
   delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, LOW);  
   delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, HIGH);  
  delay(retardo);  
}

void D() {        // Pasos a la izquierda
 digitalWrite(11, HIGH); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, HIGH);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, LOW);  
  delay(retardo); 
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, HIGH);  
 digitalWrite(8, HIGH);  
  delay(retardo); 
 digitalWrite(11, HIGH); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, HIGH);  
  delay(retardo); 
}

void S(){         // Pasos a la derecha
 digitalWrite(7, LOW); 
 digitalWrite(6, LOW);  
 digitalWrite(5, HIGH);  
 digitalWrite(4, HIGH);  
   delay(retardo); 
 digitalWrite(7, LOW); 
 digitalWrite(6, HIGH);  
 digitalWrite(5, HIGH);  
 digitalWrite(4, LOW);  
   delay(retardo); 
 digitalWrite(7, HIGH); 
 digitalWrite(6, HIGH);  
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW);  
  delay(retardo); 
 digitalWrite(7, HIGH); 
 digitalWrite(6, LOW);  
 digitalWrite(5, LOW);  
 digitalWrite(4, HIGH);  
  delay(retardo);  
}

void W() {        // Pasos a la izquierda
 digitalWrite(7, HIGH); 
 digitalWrite(6, HIGH);  
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW);  
  delay(retardo); 
 digitalWrite(7, LOW); 
 digitalWrite(6, HIGH);  
 digitalWrite(5, HIGH);  
 digitalWrite(4, LOW);  
  delay(retardo); 
 digitalWrite(7, LOW); 
 digitalWrite(6, LOW);  
 digitalWrite(5, HIGH);  
 digitalWrite(4, HIGH);  
  delay(retardo); 
 digitalWrite(7, HIGH); 
 digitalWrite(6, LOW);  
 digitalWrite(5, LOW);  
 digitalWrite(4, HIGH);  
  delay(retardo); 
}

void C(){
  int numero_pasos1 = 0;
  int numero_pasos2 = 0;
  int numero_pasos3 = 0;
  int numero_pasos4 = 0;

  while (800>numero_pasos1){   // Girohacia la izquierda en grados
         D();
         numero_pasos1 = numero_pasos1 + 1;
        }
  delay(100);
  while (800>numero_pasos2){   // Girohacia la izquierda en grados
         W();
         numero_pasos2 = numero_pasos2 + 1;
        }
  delay(100);
  while (800>numero_pasos3){   // Girohacia la izquierda en grados
         A();
         numero_pasos3 = numero_pasos3 + 1;
        }
  delay(100);
  while (800>numero_pasos4){   // Girohacia la izquierda en grados
         S();
         numero_pasos4 = numero_pasos4 + 1;
        }
  delay(100);
  }

  void V(){
  int numero_pasos1 = 0;
  int numero_pasos2 = 0;
  int numero_pasos3 = 0;
  int numero_pasos4 = 0;

  while (200>numero_pasos1){   // Girohacia la izquierda en grados
         D();
         numero_pasos1 = numero_pasos1 + 1;
        }
  delay(100);
  while (200>numero_pasos2){   // Girohacia la izquierda en grados
         W();
         numero_pasos2 = numero_pasos2 + 1;
        }
  delay(100);
  while (200>numero_pasos3){   // Girohacia la izquierda en grados
         D();
         numero_pasos3 = numero_pasos3 + 1;
        }
  delay(100);
  while (200>numero_pasos4){   // Girohacia la izquierda en grados
         W();
         numero_pasos4 = numero_pasos4 + 1;
        }
  delay(100);
  }

  void B(){
  int numero_pasos1 = 0;
  int numero_pasos2 = 0;
  int numero_pasos3 = 0;
  int numero_pasos4 = 0;
  int numero_pasos5 = 0;
  int numero_pasos6 = 0;
  int numero_pasos7 = 0;
  int numero_pasos8 = 0;
  int numero_pasos9 = 0;
  int numero_pasos10 = 0;
  int numero_pasos11 = 0;
  int numero_pasos12 = 0;
  int numero_pasos13 = 0;
  int numero_pasos14 = 0;
  int numero_pasos15 = 0;
  int numero_pasos16 = 0;
  int numero_pasos17 = 0;
  int numero_pasos18 = 0;
  int numero_pasos19 = 0;
  int numero_pasos20 = 0;
  int numero_pasos21 = 0;
  int numero_pasos22 = 0;
  int numero_pasos23 = 0;
  int numero_pasos24 = 0;
  int time=500;
  while (800>numero_pasos1){   // 1
         D();
         numero_pasos1 = numero_pasos1 + 1;
        }delay(time);
  while (800>numero_pasos2){   // 2
         W();
         numero_pasos2 = numero_pasos2 + 1;
        }  delay(time);
  while (800>numero_pasos3){   // 3
         A();
         numero_pasos3 = numero_pasos3 + 1;
        }  delay(time);
  while (800>numero_pasos4){   // 4
         S();
         numero_pasos4 = numero_pasos4 + 1;
        }  delay(time);
  while (200>numero_pasos5){   // 5
         W();
         numero_pasos5 = numero_pasos5 + 1;
        }delay(time);
  while (300>numero_pasos6){   // 6
         D();
         numero_pasos6 = numero_pasos6 + 1;
        }  delay(time);
  while (100>numero_pasos7){   // 7
         W();
         numero_pasos7 = numero_pasos7 + 1;
        }  delay(time);
  while (100>numero_pasos8){   // 8
         D();
         numero_pasos8 = numero_pasos8 + 1;
        }  delay(time);
  while (200>numero_pasos9){   // 9
         S();
         numero_pasos9 = numero_pasos9 + 1;
        }delay(time);
  while (200>numero_pasos10){   // 10
         D();
         numero_pasos10 = numero_pasos10 + 1;
        }  delay(time);
  while (200>numero_pasos11){   // 11
         W();
         numero_pasos11 = numero_pasos11 + 1;
        }  delay(time);
  while (200>numero_pasos12){   // 12
         A();
         numero_pasos12 = numero_pasos12 + 1;
        }  delay(time);
  while (200>numero_pasos13){   // 13
         W();
         numero_pasos13 = numero_pasos13 + 1;
        }delay(time);
  while (200>numero_pasos14){   // 14
         D();
         numero_pasos14 = numero_pasos14 + 1;
        }  delay(time);
  while (200>numero_pasos15){   // 15
         W();
         numero_pasos15 = numero_pasos15 + 1;
        }  delay(time);
  while (200>numero_pasos16){   // 16
         A();
         numero_pasos16 = numero_pasos16 + 1;
        }  delay(time);
  while (200>numero_pasos17){   // 17
         S();
         numero_pasos17 = numero_pasos17 + 1;
        }delay(time);
  while (100>numero_pasos18){   // 18
         A();
         numero_pasos18 = numero_pasos18 + 1;
        }  delay(time);
  while (100>numero_pasos19){   // 19
         W();
         numero_pasos19 = numero_pasos19 + 1;
        }  delay(time);
  while (300>numero_pasos20){   // 20
         A();
         numero_pasos20 = numero_pasos20 + 1;
        }  delay(time);
  while (100>numero_pasos21){   // 21
         S();
         numero_pasos21 = numero_pasos21 + 1;
        }delay(time);
  while (100>numero_pasos22){   // 22
         D();
         numero_pasos22 = numero_pasos22 + 1;
        }  delay(time);
  while (200>numero_pasos23){   // 23
         S();
         numero_pasos23 = numero_pasos23 + 1;
        }  delay(time);
  while (100>numero_pasos24){   // 24
         A();
         numero_pasos24 = numero_pasos24 + 1;
        }  delay(time);
    
    }
        
void apagado() {         // Apagado del Motor
 servo.write(90);
 digitalWrite(11, LOW); 
 digitalWrite(10, LOW);  
 digitalWrite(9, LOW);  
 digitalWrite(8, LOW);
 digitalWrite(7, LOW); 
 digitalWrite(6, LOW);  
 digitalWrite(5, LOW);  
 digitalWrite(4, LOW); 
 }
