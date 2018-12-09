# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('application', '0003_auto_20181208_1607'),
    ]

    operations = [
        migrations.AlterField(
            model_name='statistic',
            name='pdf',
            field=models.TextField(),
        ),
        migrations.AlterField(
            model_name='statistic',
            name='rooms',
            field=models.TextField(),
        ),
    ]
