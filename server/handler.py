__all__ = [
    "MyHandler"
]

import http

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

class MyHandler(
    http.server.BaseHTTPRequestHandler):  # MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST

    def do_GET(self):
        if self.path == '/favicon.ico':
            print("Skipping favicon request")
            return
        print("\nGET {}".format(str(self.path)))
        self._send_response(http.HTTPStatus.OK, self.path)

    def do_POST(self):
        content_length = int(self.headers['Content-Length'])  # <--- Gets the size of data
        post_data = self.rfile.read(content_length)  # <--- Gets the data itself
        print("\nPOST {}\nHeaders:\n{}\nBody:\n{}\n".format(str(self.path), str(self.headers),
                                                            post_data.decode('utf-8')))  #
        print(post_data.decode('utf-8'))  # print the datas that the application sent(the class)
        self._send_response(http.HTTPStatus.OK, self.path)  # send a response to the application

    def _send_response(self, code, message, info=""):
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        self.send_header('Content-type', 'text/html;charset=utf-8')
        self.end_headers()
        print(self.path)
        try:
            content = (DEFAULT_MESSAGE_TEMPLATE.format(code=code, message=_quote_html(message),
                                                       info=_quote_html(info)))  # formats the default message
            self.wfile.write(
                content.encode('UTF-8', 'replace'))  # send the DEFAULT_MESSAGE_TEMPLATE(formatted) to the client
            self.wfile.flush()  # channel free
            return True
        except Exception as ex:
            print("ERRORE in send_response: " + str(ex))
            return False
