__all__ = [
    "DataHandler"
]

class DataHandler():
    datasPath = 'data/'

    # all the files with the JSON datas must be called 'roomContent.txt'
    def getContent(self, room = 'example'):
        roomDataDir = self.datasPath + 'room' + room + '/roomContent.txt' # dir : 'data/roomN/roomContent.txt'
        f = open(roomDataDir, 'r')
        return f.read()

    def getImageName(self, room = 'example', image = '1'):
        return self.datasPath + 'room' + room + '/image'+ image
