import database
import mysqldb
from redisdb import *


class dbFactory():
    def getDB(self,type):
        if ("mysql" in type):
            return mysqldb.mysqldb()
        else:
            return redisdb()
