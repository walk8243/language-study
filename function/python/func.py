import datetime

date_format = '%Y/%m/%d %H:%M:%S'

def print_now():
    dt_now = datetime.datetime.now()
    print(format_date(dt_now))

def format_date(datetime: datetime.datetime):
    return datetime.strftime(date_format)
