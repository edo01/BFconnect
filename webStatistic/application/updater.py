import json
from application.models import Statistic
import requests
from threading import Thread
import time

__all__ = [
    "Updater"
]

class Updater (Thread):

   label = []
   responses = []
   errors = []
   counter = 1

   def __init__(self, nome, durata):
      Thread.__init__(self)
      self.nome = nome
      self.durata = durata

   def run(self):
       while True:
            print('aggiornamento')
            self.upload()
            time.sleep(self.durata)

   def setRequest(self, request):
       self.request = request

   def upload(self):
       try:
            r = requests.get('http://taddia.sytes.net:6002/?check=true')
            print(r.status_code)
            print(r.text)
            dic = json.loads(r.text)
       except:
           dic = json.loads('{"middleTimeOfResponse":0,"responses":0,"errors":0,"rooms":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"pdf":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}')

       if len(self.label)==60:
           self.responses.remove(self.responses[0])
           self.errors.remove(self.errors[0])
       else:
           self.label.append(len(self.label) + 1)

       self.responses.append(dic['responses'])
       self.errors.append(dic['errors'])
       newStatistic = Statistic(errors=dic['errors'],middleTimeOfResponse = dic['middleTimeOfResponse'], responses =dic['responses'],
                 pdf=dic['pdf'], rooms=dic['rooms'])
       newStatistic.save()
       print(newStatistic)





