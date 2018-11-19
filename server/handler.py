__all__ = [
    "MyHandler"
]

import http

# Default message template
DEFAULT_MESSAGE_TEMPLATE = """\
{
"title":"aula informatica",
"content":"questa è l'aula di informatica del Belluzzi Fioravanti"
}
"""


class MyHandler(
    http.server.BaseHTTPRequestHandler):  # MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST

    def do_GET(self):
        if self.path == '/favicon.ico':
            print("Skipping favicon request")
            return
        print("\nGET {}".format(str(self.path)))
        print(self.path)
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        self.send_header('Content-type', 'image/png;charset=utf-8')
        self.end_headers()
        self.wfile.write(self.load("ic_bf_connect_horizontal.png"))
        self.wfile.flush()


    def do_POST(self):
        content_length = int(self.headers['Content-Length'])  # <--- Gets the size of data
        post_data = self.rfile.read(content_length)  # <--- Gets the data itself
        print("\nPOST {}\nHeaders:\n{}\nBody:\n{}\n".format(str(self.path), str(self.headers),
                                                            post_data.decode('utf-8')))  #
        print(post_data.decode('utf-8'))  # print the datas that the application sent(the class)
        self._send_response(http.HTTPStatus.OK, self.path)  # send a response to the application

    def _send_response(self, code, message, info=""):
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        self.send_header('Content-type', 'json;charset=utf-8')
        self.end_headers()
        print(self.path)
        try:
            content = DEFAULT_MESSAGE_TEMPLATE.format # formats the default message
            self.wfile.write(
                content.encode('UTF-8', 'replace'))  # send the DEFAULT_MESSAGE_TEMPLATE(formatted) to the client
            self.wfile.flush()  # channel free
            return True
        except Exception as ex:
            print("ERRORE in send_response: " + str(ex))
            return False

    def load(self,file):
        f=open(file, 'rb')
        s=f.read()
        return s

    def encode(self,file):
        return bytes(file, 'UTF-8')
