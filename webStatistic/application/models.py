from django.db import models
from django.utils import timezone


# if you want to update the db you have to write first:
# python manage.py makemigrations application
# and after:
# python manage.py migrate application

class Statistic(models.Model):
    errors = models.IntegerField()

    middleTimeOfResponse = models.FloatField()

    responses = models.IntegerField()

    pdf = models.TextField()

    rooms = models.TextField()

    created_date = models.DateTimeField(
        default=timezone.now)

    def __str__(self):
        return str(self.created_date)
