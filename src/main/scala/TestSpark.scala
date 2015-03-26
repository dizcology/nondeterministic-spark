import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

import scala.util.Random

object FirstK {
  var a = Array[Int]()
  var k = 1
  def insert(x: Int) = {
    if (a.size < k) {
      a = (a :+ x).sorted
    } else {
      if (x < a(k-1)) {
        a(k-1) = x
        a = a.sorted
      }
    }
    true
  }
}

object TestSpark {
  def main(args: Array[String]) {
    val conf = new SparkConf()
                  .setMaster("local[4]")
                  .set("spark.ui.port", "4041")
                  
    val sc = new SparkContext(conf)
    
    val n = 1000
    val k = 10
    
    FirstK.k = k
    val values = sc.parallelize(Random.shuffle(1 to n))
    
    for (t <- values) { FirstK.insert(t) }
    
    println(FirstK.a.deep)
  
  }

}