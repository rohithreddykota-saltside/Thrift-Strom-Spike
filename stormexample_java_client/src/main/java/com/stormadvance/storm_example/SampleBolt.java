package com.stormadvance.storm_example;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

public class SampleBolt extends BaseBasicBolt {
    private static final long serialVersionUID = 1L;

    public void execute(Tuple input, BasicOutputCollector collector){
        String test = input.getStringByField("site");
        System.out.println("name of the input site is: " + test);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer){

    }
}
