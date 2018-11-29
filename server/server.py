#!/usr/bin/env python3.6

"""
BFServer:
XXX To do:
"""

# BFCONNECT Working Group
# - E. Carrà, F. Taddia, R. Bovinelli

__version__ = "0.1c"


import http.server
from handler import MyHandler
import datetime

def run():
    myserver = None
    server_name = "BFServer"
    server_address = ('10.72.1.13',6002) #the server listens on 6002 port
    server_startingtime = datetime.datetime.now()
    print("Starting server", str(server_startingtime), "...")
    try:
        myserver = http.server.HTTPServer(server_address, MyHandler) # the constructor of the class http.server.HTTPServer

        print( server_name, "started on port", myserver.server_port )
        print('\n')
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


