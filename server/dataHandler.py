__all__ = [
    "DataHandler"
]

class DataHandler():
    datasPath = 'data/'
    pdf = { '9':'elettronica.pdf', '1' :'informatica.pdf', '3' :'chimica.pdf', '2' :'meccanica.pdf', '4':'serale.pdf', '5':'serale.pdf', '6':'qualifiche.pdf',
            '8':'apparati.pdf', '7':'manutenzione.pdf', '10':'mezzi.pdf'}

    # all the files with the JSON datas must be called 'roomContent.txt'
    def getContent(self, room = 'example'):
        roomDataDir = self.datasPath + 'room' + room + '/roomContent.txt' # dir : 'data/roomN/roomContent.txt'
        f = open(roomDataDir, 'r')
        s = f.read()
        f.close()
        return s

    def getImageName(self, room = 'example', image = '1'):
        return self.datasPath + 'room' + room + '/image'+ image

    def getPdfName(self, number = 0):
        if number == 0:
            return 'orariTecnico.pdf'
        try:
            return 'pdf/'+self.pdf[number]
        except:
            return 'orariTecnico.pdf'
