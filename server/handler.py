__all__ = [
    "MyHandler"
]

import http
from dataHandler import DataHandler
from urllib.parse import parse_qs
from timeit import default_timer as timer

# Default message template
DEFAULT_ERROR_MESSAGE = """\
ERROR IN SENDING MESSAGE
"""

class MyHandler(
    http.server.BaseHTTPRequestHandler):  # MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST

    datahandler = DataHandler()

    def do_GET(self):
        if self.path == '/favicon.ico' and str(self.path).find('?')==-1:
            return
        start = timer()
        #print("\nGET {}".format(str(self.path)))
        #in the application the url must be 'https://ip/?room=number&image=something&pdf=something'
        keys = parse_qs(self.path[2:])
        try:
            room = str(keys['room']).replace('[\'', '').replace('\']', '')
            image = str(keys['image']).replace('[\'', '').replace('\']', '')
            pdf = str(keys['pdf']).replace('[\'', '').replace('\']', '')
            if pdf != 'false':
                self.sendPdf(pdf)
                print('pdf inviato: ', self.datahandler.pdf[pdf])
            elif image.isdecimal():
                self.sendImage(room, image)
                print('la room selezionata è :', room)
                print('immagine richiesta:', image)
            else:
                self.sendContent(room)
                print('la room selezionata è :', room)
                print('immagine richiesta: nessuna')
            end = timer()
            print('response in:', end - start)
        except:

            print('error in sending response')


    def doHead(self, contentType = 'txt'):  #adding headers
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        contentType = contentType + ';charset=utf-8;'
        self.send_header('Content-type', contentType)
        self.end_headers()

    def sendContent(self, room = 'example'): #send title, description etc..
        self.doHead('json')
        self.wfile.write(self.datahandler.getContent(room).encode('UTF-8', 'replace'))  # before sending message must be encode
        self.wfile.flush()

    def sendPdf(self, pdf = "0"): #send title, description etc..
        self.doHead('pdf')
        self.wfile.write(self.load(self.datahandler.datasPath + self.datahandler.getPdfName(pdf)))  # before sending message must be encode
        self.wfile.flush()

    def sendImage(self, room = 'example', image = 1): #send images
        self.doHead('image')
        self.wfile.write(self.load(self.datahandler.getImageName(room, image)))
        self.wfile.flush()

    def load(self,file): #open image
        f=open(file, 'rb')
        s=f.read()
        f.close()
        return s

    def encode(self,file): #encoding image
        return bytes(file, 'UTF-8')

    def do_POST(self):
        self.do_GET()
