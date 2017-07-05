#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

//const char* ssid = "kokamonga"; 
//const char* password = "put03lqu3l33";

const char* ssid = "RPi_SERVER"; 
const char* password = "ladesiempre";

byte ipraspberry[] = { 192 , 168 , 200 , 1 };
int ledPin = 13; // GPIO13
int idBoard = 1234; // esta bien int?????
WiFiServer server(3048);

WiFiClient client;
IPAddress miIP;

//TODO arreglar el tema de la ultima barra 
String getUrlSegment(String url, int index){
    int found = 0;
    int strIndex[] = { 0, -1 };
    int maxIndex = url.length() - 1;
    String separator = "/";

    for (int i = 0; i <= maxIndex && found <= index; i++) {
        if (url.charAt(i) == '/' || i == maxIndex) {
            found++;
            strIndex[0] = strIndex[1] + 1;
            strIndex[1] = (i == maxIndex) ? i+1 : i;
        }
    }
    return found > index ? url.substring(strIndex[0], strIndex[1]) : "";
}

//este metodo transforma del fortmato int de ip a string claramente 
String ipToString(IPAddress ip){
  String s="";
  for (int i=0; i<4; i++)
    s += i  ? "." + String(ip[i]) : String(ip[i]);
  return s;
}


void setup() {
  Serial.begin(115200);
  delay(10);
  pinMode(ledPin, OUTPUT);
  digitalWrite(ledPin, LOW);
 
  // Connect to WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
 
  // Start the server
  server.begin();
  Serial.println("Server started");
  miIP = WiFi.localIP();
  
  //trying to announce to rasberry
 if (client.connect(ipraspberry,3047)) {  
    String ip = ipToString(miIP);
    Serial.println("Connected to Raspberry");
    client.print(String("GET ") + "/announcement/board?id=1234&ip=" + ip + "&code=node" + " HTTP/1.1\r\n" +
               "Host: " + "192.168.1.107:3047" + "\r\n" + 
               "Connection: close\r\n\r\n");
    Serial.println("http://192.168.1.107:3047/announcement/board?id=1234&ip=" + ip + "&code=node");
  } else {
    Serial.println("Couldn't connect to Raspberry");
  }
}

 
void loop() {
  // Check if a client has connected
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  // Wait until the client sends some data
  Serial.println("new client");
  while(!client.available()){
    delay(1);
  }
 
  // Read the first line of the request
  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();
  client.stop();

  int value = LOW;
  int idBoard_URL;
  int pin_URL;
  String action_URL;
  int value_URL;

  
 idBoard_URL=getUrlSegment(request, 1).toInt();
 pin_URL=getUrlSegment(request, 2).toInt();
 action_URL=getUrlSegment(request, 3);
  value_URL=getUrlSegment(request, 4).toInt();

if (idBoard_URL == idBoard) {
  
   if  (action_URL == "on")   { 
      digitalWrite(pin_URL, HIGH);
     value = HIGH;
    } 
 
    if (action_URL == "off") {
      digitalWrite(pin_URL, LOW);
      value = LOW;
  }

  delay(1);

  }
}




