<div align="center"><img src="application/app/src/main/res/drawable/ic_bf_connect_horizontal.png" alt="BFconnect Logo"><br>
<h2>THE FIRST APP OF I.I.S. BELLUZZI FIORAVANTI</h2></div>
<br>
<h3 align="center">BFconnect is an innovative app that will allow schools to manage their Open Day in a better way.</h3><br>
<h3 align="center">Powered by Edoardo Carrà and Francesco Taddia</h3>
<div>
<a align="center" href="https://play.google.com/store/apps/details?id=org.iisbelluzzifioravanti.app.bfconnect" target="_blank"> <img src="application/app/src/main/res/drawable/badge_playstore.png" width="170" height="50"></a>
</div>
<div>
<h2>TO RUN APP SERVER:</h2>
  <h3>server directory: "BFconnect/server/".</h3>
  <h4>The structure of the server must be like this: </h4>
<h4>
<ul>
<li>server.py</li>
<li>dataHandler.py</li>
<li>handler.py</py>
<li>local_settings.template</li>
<li>data:<ul><li>pdf:<ul><li>1.pdf</li><li>2.pdf</li><li>...</li></ul></li>
	     <li>room0<ul><li>roomContent.txt</li><li>image1</li></ul></li>
	     <li>room1<ul><li>roomContent.txt</li><li>image1</li></ul></li>
	     <li>room2<ul><li>roomContent.txt</li><li>image1</li></ul></li>
	     <li>room...<ul><li>roomContent.txt</li><li>image1</li></ul></li>	
</ul></li>
</ul>
</h4>
<br><br>
<h4>Inside "roomContent.txt" file you can find JSON data which contain all the informations about the room. Inside "pdf" directory are contained chool'school's specializations' time tables. The dictionary look like this:<br>
pdf = {
        '4': 'elettronica.pdf', '1': 'informatica.pdf', '3': 'chimica.pdf', '2': 'meccanica.pdf', '9': 'serale.pdf',
        '5': 'serale.pdf', '6': 'qualifiche.pdf','8': 'apparati.pdf', '7': 'manutenzione.pdf', '10': 'mezzi.pdf'
    }
</h4>
<h4>RUN SERVER:
<ol>
<li>Create a setting file in your server dirctory named "local_settings.py" and follow the template in "local_settings.template" .<br>
Here you can set the port and the ip_address of your server.
<br>Attention! Your settings must be synced with the application server host url.</li>
<li>in your server directory run : <br>		python server.py</li>
</ol>
</h4>
</div>
<div>
<h2>TO RUN STATISTIC SERVER:</h2>
  <h3>Server directory: "BFconnect/webStatistic/".</h3>
  <h4>using framework django ver = 1.8.1</h4>
  <h4>Django reference online:https://docs.djangoproject.com/en/1.8/ref/ .</h4>
  <h4>run server:</h4>
<h4>
<ol type="1">
  <li>Change directory into webStatistic;</li>
  <li>Activate the virtual enviroment: source ".venv/bin/activate";</li>
  <li>Run the server with the command: "python manage.py localhost:8000" if you want you can change the ip and the port(like ip:port).</li>
</ol>
</h4>
</div>
