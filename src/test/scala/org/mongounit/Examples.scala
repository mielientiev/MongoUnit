package org.mongounit

import org.mongodb.scala.MongoClient
import org.scalatest.{Matchers, WordSpec}

import scala.concurrent.Await
import scala.concurrent.duration._

class Examples extends WordSpec with Matchers with MongoScalaTest {

  lazy val mongoClient = MongoClient(s"mongodb://$embedConnectionURL:$embedConnectionPort")

  "MongoUnit" should {
    "load single json from resources" in {
      LoadFromResource("./testdata/singleJson.json") ~> {
        val result = mongoClient.getDatabase(defaultDatabase).getCollection(defaultCollection).find().toFuture()
        val report = Await.result(result, 10.second)
        report should have size 1
        report.head.get("id").foreach(x => x.asString().getValue shouldEqual "111-111-111-111")
      }
    }

    "load several files" in {
      LoadFromResource("./testdata/singleJson.json") ~>
        LoadFromResource("./testdata/singleJson.json") ~>
        LoadFromResource("./testdata/singleJson.json") ~> {
        val result = mongoClient.getDatabase(defaultDatabase).getCollection(defaultCollection).find().toFuture()
        val report = Await.result(result, 10.second)
        report should have size 3
        report.head.get("id").foreach(x => x.asString().getValue shouldEqual "111-111-111-111")
      }
    }
  }

}
