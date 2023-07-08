import csv
import time
import random
import json
import datetime
from datetime import date
from dateutil.relativedelta import relativedelta
from json import JSONEncoder
from locust import HttpUser, TaskSet, task, between, constant


class DateTimeEncoder(JSONEncoder):
    # Override the default method
    def default(self, obj):
        if isinstance(obj, (datetime.date, datetime.datetime)):
            return obj.isoformat()


class UserBehaviour(HttpUser):
    wait_time = between(1, 5)
    response_data_id = []
    rows, cols = 5, 5

    @task
    def callEndpoint(self):

        r_dati = [
            [375, 'ASDF343WDFDS'],
            [376, 'ASDF343WDFDS'],
            [377, 'ASDF343WDFDS'],
            [378, 'CFGRER444EEE'],
            [379, 'CFGRER444EEE'],
            [380, 'SARWERDC444XXX22']
        ]
        davEndpoint = "http://my-library-alb-1738683653.eu-north-1.elb.amazonaws.com:8083/prestito/libro/esegui"
        #davEndpoint = "http://localhost:8083/prestito/libro/esegui"
        for x in r_dati:
            col = []
            future_date = datetime.datetime.now() + relativedelta(months=2)
            datijson = {
                "BOOK_ID": x[0],
                "COD_FISCALE": str(x[1]),
                "DATE_LENDING": datetime.datetime.now(),
                "RETURN_DATE": future_date
            }
            encodedJSONData = json.dumps(datijson, indent=4, cls=DateTimeEncoder)
            print(encodedJSONData)
            response = self.client.post(
                davEndpoint,
                json={
                "BOOK_ID": x[0],
                "COD_FISCALE": str(x[1]),
                "DATE_LENDING":"2023-07-05T18:06:05.053687",
                "RETURN_DATE": "2023-09-05T18:06:05.053687"
            },
                auth=None,
                headers={
                    "SERVICE-AUTH-TOKEN": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNeVNlY3JldEtleVRvQ2FsbFNlcnZpY2UiLCJpc3MiOiJsaWJyYXJ5LWxlbmRpbmctc2VydmljZSJ9.HcnbMGC_DEz4NkneyQcUsFEOGjYAZd5tK2cB9kE0S3IqGvoeGGGUxF12BGJJA5lQHk2OyXfUdR0CooHtKq9DvQ"}
            )
            json_var = response.json()
            print(json_var)
            status = str(json_var['status'])
            if status != '207':
                # print("dentro: ", status)
                request_id = json_var['data']['LEND_ID']
                col.append(request_id)
                col.append(x[0])
                self.response_data_id.append(col)
                print(self.response_data_id)

    @task
    def callEndpoint2(self):
        r_dati = [
            [375, 'ASDF343WDFDS'],
            [376, 'ASDF343WDFDS'],
            [377, 'ASDF343WDFDS'],
            [378, 'CFGRER444EEE'],
            [379, 'CFGRER444EEE'],
            [380, 'SARWERDC444XXX22']
        ]

        if self.response_data_id:
            lista = self.response_data_id.pop(0)
            # print("RDATA: ", lista)
            davEndpoint2 = "http://my-library-alb-1738683653.eu-north-1.elb.amazonaws.com:8083/prestito/libro/return"
            #davEndpoint2 = "http://localhost:8083/prestito/libro/return"
            # for x in r_dati:
            response2 = self.client.put(
                davEndpoint2,
                json={
                    "LEND_ID": lista[0],
                    "BOOK_ID": lista[1]
                }
            )
            json_var2 = response2.json()

    @task
    def callEndpoint3(self):
        davEndpoint3 = "http://my-library-alb-1738683653.eu-north-1.elb.amazonaws.com:8081/libri/lista/"
        #davEndpoint3 = "http://localhost:8081/libri/lista/"
        for x in range(6):
            davEndpoint3 = davEndpoint3 + chr(random.randint(ord('a'), ord('z')))

        response3 = self.client.get(
            davEndpoint3
        )
        json_var3 = response3
