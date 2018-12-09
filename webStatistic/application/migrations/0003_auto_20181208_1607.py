# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('application', '0002_auto_20181208_1005'),
    ]

    operations = [
        migrations.RenameField(
            model_name='statistic',
            old_name='middleTimeOfRequest',
            new_name='middleTimeOfResponses',
        ),
        migrations.RenameField(
            model_name='statistic',
            old_name='Requests',
            new_name='pdf',
        ),
        migrations.AddField(
            model_name='statistic',
            name='responses',
            field=models.IntegerField(default=2),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='statistic',
            name='rooms',
            field=models.IntegerField(default=3),
            preserve_default=False,
        ),
    ]
