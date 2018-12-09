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
       r = requests.get('http://localhost:8080/?check=true')
       print(r.text)
       dic = json.loads(r.text)

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





