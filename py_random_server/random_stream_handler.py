import sys
sys.path.append("../gen-py")
from random_stream import streamService
import time

class RandomStreamHandler(streamService.Iface):
    def stream(self, num):
        print("got request at {}".format(time.time()))
        return "you have requested for "+str(num)+"!"