__all__ = [
    "MyHandler"
]

import http

# Default message template
DEFAULT_MESSAGE_TEMPLATE = """\
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/page"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".Prenotation">

    <CalendarView
        android:id="@+id/calendarView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_alignParentEnd="true"
        android:layout_centerHorizontal="false"
        android:layout_marginStart="21dp"
        android:layout_marginTop="36dp"
        android:layout_marginEnd="15dp" />

</RelativeLayout>
"""

def _quote_html(html):
    return html.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")

class MyHandler(
    http.server.BaseHTTPRequestHandler):  # MyHandler extends http.server.BaseHTTPRequestHandler and implements the method do_Get and do_POST

    def do_GET(self):
        if self.path == '/favicon.ico':
            print("Skipping favicon request")
            return
        print("\nGET {}".format(str(self.path)))
        print(self.path)
        self.wfile.write(self.load("ic_bf_connect_horizontal.png"))
        # self._send_response(http.HTTPStatus.OK, self.path)

    def do_POST(self):
        content_length = int(self.headers['Content-Length'])  # <--- Gets the size of data
        post_data = self.rfile.read(content_length)  # <--- Gets the data itself
        print("\nPOST {}\nHeaders:\n{}\nBody:\n{}\n".format(str(self.path), str(self.headers),
                                                            post_data.decode('utf-8')))  #
        print(post_data.decode('utf-8'))  # print the datas that the application sent(the class)
        self._send_response(http.HTTPStatus.OK, self.path)  # send a response to the application

    def _send_response(self, code, message, info=""):
        self.send_response(http.HTTPStatus.OK)  # it is a protocol
        self.send_header('Content-type', 'image/png;charset=utf-8')
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

    def load(self,file):
        f=open(file, 'r')
        return self.encode(str(f.read()))

    def encode(self,file):
        return bytes(file, 'UTF-8')
