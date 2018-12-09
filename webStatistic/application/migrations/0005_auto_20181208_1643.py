# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('application', '0004_auto_20181208_1629'),
    ]

    operations = [
        migrations.RenameField(
            model_name='statistic',
            old_name='middleTimeOfResponses',
            new_name='middleTimeOfResponse',
        ),
    ]
