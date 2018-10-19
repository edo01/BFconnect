#!/usr/bin/env python3.6
"""
BelluzzServer:
XXX To do:
- send video and images
"""

# Belluzzi Python Working Group
# - E. Carrà, F. Taddia

__version__ = "0.1c"

__all__ = [
    "TraceServer", "TraceRequestHandler"
]

import http.server
from http import HTTPStatus
import datetime

# Default message template
DEFAULT_MESSAGE_TEMPLATE = """\
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
        <title>Belluzz Server Response</title>
    </head>
    <body>
        <h1>Server Response</h1>
        <h1>BELLUZZ SERVER is working</h1>
        <form>
            please insert class:<br>
            <input type="text" name="class"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
"""


def _quote_html(html):
    return html.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

class MyHandler(http.server.BaseHTTPRequestHandler): #MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST
    
    def do_GET(self):
        if self.path == '/favicon.ico':
            print("Skipping favicon request")
            return
        print( "\nGET {}".format(str(self.path)) )
        self._send_response(HTTPStatus.OK, self.path)
        
    def do_POST(self):
        content_length = int(self.headers['Content-Length']) # <--- Gets the size of data
        post_data = self.rfile.read(content_length) # <--- Gets the data itself
        print( "\nPOST {}\nHeaders:\n{}\nBody:\n{}\n".format(str(self.path), str(self.headers), post_data.decode('utf-8'))) #
        print(post_data.decode('utf-8')) #print the datas that the application sent(the class)
        self._send_response(HTTPStatus.OK, self.path)#send a response to the application

    def _send_response(self, code, message, info=""):
        self.send_response(HTTPStatus.OK)#it is a protocol
        self.send_header('Content-type','text/html;charset=utf-8')
        self.end_headers()
        print(self.path)
        try:
            content = ( DEFAULT_MESSAGE_TEMPLATE.format(code=code, message=_quote_html(message), info=_quote_html(info)) )#formats the default message
            self.wfile.write( content.encode('UTF-8', 'replace') )#send the DEFAULT_MESSAGE_TEMPLATE(formatted) to the client
            self.wfile.flush() #channel free
            return True
        except Exception as ex:
            print("ERRORE in send_response: " + str(ex))
            return False

def run():
    myserver = None
    server_name = "BelluzzServer"
    server_address = ('',80) #the server listens on 80 port
    server_startingtime = datetime.datetime.now()
    print("Starting server", str(server_startingtime), "...")
    try:
        myserver = http.server.HTTPServer(server_address, MyHandler) # the constructor of the class http.server.HTTPServer
        print( server_name, "started on port", server_address[1] )
        myserver.serve_forever()#started to listen on the port specificated
    except (KeyboardInterrupt, SystemExit):
        pass
    except Exception as ex:
        print("ERROR server: " + str(ex))
    print("Stopping server ...")
    myserver.server_close()
    print("Server stopped!")
    

if __name__ == '__main__':
    run()

