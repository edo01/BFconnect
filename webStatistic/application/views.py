from django.shortcuts import render
from .models import Statistic
from .updater import Updater

up = Updater('2', 30)

def showDashboard(request):
    if(not up.is_alive()):
        print('aggiornamento non in corso, attivazione:')
        up.start()
    newStatistic = Statistic.objects.order_by('-created_date')[0] # taking the lst statistic in db
    return render(request, 'application/statistic.html', {'statistic': newStatistic, 'label': Updater.label, 'errors':
                                                          Updater.errors, 'responses': Updater.responses})
