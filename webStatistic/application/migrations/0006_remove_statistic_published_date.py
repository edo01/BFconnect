# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('application', '0005_auto_20181208_1643'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='statistic',
            name='published_date',
        ),
    ]
