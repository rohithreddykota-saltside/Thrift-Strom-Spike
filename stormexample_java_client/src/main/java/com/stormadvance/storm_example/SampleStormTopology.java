package com.stormadvance.storm_example;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;

public class SampleStormTopology {
    public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("sampleSpout", new SampleSpout(), 2);
        builder.setBolt("sampleBolt", new SampleBolt(), 4).shuffleGrouping("sampleSpout");
        Config conf = new Config();
        conf.setDebug(true);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("sampleStormTopology", conf, builder.createTopology());
        try{
            Thread.sleep(1000000);
        }catch (Exception exception){
            System.out.println("Thread Intrrupted exception: " + exception);
        }
        cluster.killTopology("sampleStormTopology");
        cluster.shutdown();
    }
}
