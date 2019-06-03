package com.stormadvance.storm_example;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import randomStream.streamService;

import java.util.Map;
import java.util.Random;

public class SampleSpout extends BaseRichSpout {

    TTransport transport = new TSocket("localhost", 9090);
    TProtocol protocol = new  TBinaryProtocol(transport);
    streamService.Client client = new streamService.Client(protocol);

    private static final long serialVersionUID = 1L;

    private SpoutOutputCollector spoutOutputCollector;
    public void open(Map conf, TopologyContext context, SpoutOutputCollector spoutOutputCollector){
        this.spoutOutputCollector = spoutOutputCollector;
    }

    public void nextTuple(){
        try {
            transport.open();
            final Random rand = new Random();
            int randomNumber = rand.nextInt(5);
            spoutOutputCollector.emit(new Values(client.stream(randomNumber)));
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }

        try{
            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println("Failed to sleep the thread");
        }
    }
    public void declareOutputFields(OutputFieldsDeclarer declarer){
        declarer.declare(new Fields("site"));
    }
}
