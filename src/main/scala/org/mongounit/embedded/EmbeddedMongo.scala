package org.mongounit.embedded

import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.{MongodExecutable, MongodProcess, MongodStarter}
import org.mongodb.scala.MongoClient


trait EmbeddedMongo {

  private lazy val runtime: MongodStarter = MongodStarter.getDefaultInstance
  private lazy val config = new MongodConfigBuilder().net(new Net(embedConnectionPort.toInt, true)).version(embedMongoDBVersion).build()
  private[mongounit] lazy val mongodExe: MongodExecutable = runtime.prepare(config)
  private[mongounit] lazy val mongod: MongodProcess = mongodExe.start()
  private[mongounit] lazy val mongoDB = MongoClient(s"mongodb://$embedConnectionURL:$embedConnectionPort")

  def embedConnectionURL: String = "localhost"

  def embedConnectionPort: String = "22112"

  def embedMongoDBVersion: Version = Version.V3_3_1

}
