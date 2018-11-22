__all__ = [
    "MyHandler"
]

import http
from dataHandler import DataHandler
from urllib.parse import parse_qs

# Default message template
DEFAULT_MESSAGE_TEMPLATE = """\
{
"title":"ITIS BELLUZZI",
"content":"Benvenuti all'open day del Belluzzi Fioravanti"
}
"""


class MyHandler(
    http.server.BaseHTTPRequestHandler):  # MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST

    datahandler = DataHandler()

    def do_GET(self):
        if self.path == '/favicon.ico':
            #print("Skipping favicon request")
            return
        #print("\nGET {}".format(str(self.path)))
        keys = parse_qs(self.path[2:]) #in the application the url must be 'https://ip/?room=number&image=something'
        #print(keys)
        room = str(keys['room']).replace('[\'','').replace('\']','')
        image = str(keys['image']).replace('[\'','').replace('\']','')

        if image == 'false':
            self.sendContent(room)
            print('la room selezionata è :', room)
            print('immagine richiesta: nessuna')
        else :
            self.sendImage(room, image)
            print('la room selezionata è :', room)
            print('immagine richiesta:', image)

    def doHead(self, contentType = 'txt'):  #adding headers
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        contentType = contentType + ';charset=utf-8;'
        self.send_header('Content-type', contentType)
        self.end_headers()

    def sendContent(self, room = 'example'): #send title, description etc..
        self.doHead('json')
        self.wfile.write(self.datahandler.getContent(room).encode('UTF-8', 'replace'))  # before sending message must be encode
        self.wfile.flush()

    def sendImage(self, room = 'example', image = 1): #send images
        self.doHead('image')
        self.wfile.write(self.load(self.datahandler.getImageName(room, image)))
        self.wfile.flush()

    def load(self,file): #open image
        f=open(file, 'rb')
        s=f.read()
        return s

    def encode(self,file): #encoding image
        return bytes(file, 'UTF-8')

    def do_POST(self):
        self.do_GET()
