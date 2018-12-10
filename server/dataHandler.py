__all__ = [
    "DataHandler"
]

import json


class DataHandler:
    time = 0
    # the number of nuumbers in rooms is variable
    statistic = '{"middleTimeOfResponse":0,"responses":0,"errors":0,"rooms":[0,0,0,0],"pdf":[0,0,0,0,0,0,0,0]}'
    dictionary = json.loads(statistic)

    datasPath = 'data/'
    pdf = {
        '4': 'elettronica.pdf', '1': 'informatica.pdf', '3': 'chimica.pdf', '2': 'meccanica.pdf', '9': 'serale.pdf',
        '5': 'serale.pdf', '6': 'qualifiche.pdf','8': 'apparati.pdf', '7': 'manutenzione.pdf', '10': 'mezzi.pdf'
    }

    room = {
        'belOPEN': '0', 'ETC34c': '1', 'ETC620': '2', 'ETC34d': '3'
    }

    # all the files with the JSON datas must be called 'roomContent.txt'
    def getContent(self, room='belOPEN'):
        roomDataDir = self.datasPath + 'room' + self.room[room] + '/roomContent.txt'  # dir : 'data/roomN/roomContent.txt'
        self.addRoom(int(self.room[room]))
        f = open(roomDataDir, 'r')
        s = f.read()
        f.close()
        return s

    # it's not necessary to addRoom because if somebody requests the content of a room he requested first the content
    def getImageName(self, room='belOPEN', image='1'):
        return self.datasPath + 'room' + self.room[room] + '/image' + image

    def getPdfName(self, number=0):
        if number == 0:
            return 'orariTecnico.pdf'
        try:
            self.addPdf(int(number) - 1)
            return 'pdf/' + self.pdf[number]
        except:
            return 'orariTecnico.pdf'

    def addResponseTime(self, time):
        self.time += time
        self.dictionary['responses'] += 1
        self.dictionary['middleTimeOfResponse'] = float("{0:.8f}".format(self.time / self.dictionary['responses']))
        print(self.dictionary)

    def addError(self):
        self.dictionary['errors'] += 1

    def addRoom(self, room):
        self.dictionary['rooms'][room] += 1

    def addPdf(self, pdf):
        self.dictionary['pdf'][pdf] += 1

    def getStatistic(self):
        return json.dumps(self.dictionary)
