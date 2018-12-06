#!/usr/bin/env python3.6

"""
BFServer:
XXX To do:
"""

# BFCONNECT Working Group
# - E. Carrà, F. Taddia, R. Bovinelli
from socketserver import ThreadingMixIn

__version__ = "0.1c"


from http.server import HTTPServer
from handler import MyHandler
import datetime
import local_settings

#to add certificate use: openssl req -x509 -newkey rsa:4096 -nodes -out cert.pem -keyout key.pem -days 365
USE_HTTPS = False

class ThreadingSimpleServer(ThreadingMixIn, HTTPServer):
    pass


def run():
    myserver = None
    server_name = "BFServer"
<<<<<<< HEAD
    server_address = (local_settings.ip_address, local_settings.port) #the server listens on 6002 port
=======
    server_address = ('10.72.1.6',6002) #the server listens on 6002 port
>>>>>>> e3b4696f279853f4b38b3fc4391575e379eb65d9
    server_startingtime = datetime.datetime.now()
    print("Starting server", str(server_startingtime), "...")
    try:
        server = ThreadingSimpleServer(server_address, MyHandler)
        if USE_HTTPS:
            import ssl
            server.socket = ssl.wrap_socket(server.socket, keyfile='./key.pem', certfile='./cert.pem', server_side=True)

        print( server_name, "started on port", server.server_port)
        print('\n')
        server.serve_forever()#started to listen on the port specificated
    except (KeyboardInterrupt, SystemExit):
        pass
    except Exception as ex:
        print("ERROR server: " + str(ex))
    print("Stopping server ...")
    server.server_close()
    print("Server stopped!")
    

if __name__ == '__main__':
    run()


