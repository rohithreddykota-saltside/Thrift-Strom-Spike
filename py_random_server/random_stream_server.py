import sys
sys.path.append("../gen-py")
sys.path.append("..")
from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from random_stream import streamService
from py_random_server.random_stream_handler import RandomStreamHandler
import traceback

if __name__=='__main__':
    try:
        handler = RandomStreamHandler()
        server = TServer.TThreadedServer(
            streamService.Processor(handler),
            TSocket.TServerSocket(host="127.0.0.1", port = 9090),
            TTransport.TFramedTransportFactory(),
            TBinaryProtocol.TBinaryProtocolFactory()
        )
        print("initializing server...")
        server.serve()
    except Exception as ex:
        traceback.format_exc(ex)
        sys.exit(1)

