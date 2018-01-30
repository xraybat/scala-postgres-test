/**
  * Created by psc on 9/11/17.
  * classes that represent tables in a database
  */

package au.com.carringbushsw.Akka.database

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.{ Await, Future } //@TODO remove 'Await' once no longer esed
import scala.util.{ Try, Success, Failure  }
import scala.concurrent.duration.Duration //@TODO remove once 'Await' no longer used
import scala.concurrent.ExecutionContext.Implicits.global

case class Actor(id: Int, name: String, url: Option[String])

class Actors(tag: Tag) extends Table[Actor](tag, "actors") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")       // not null
  def url = column[Option[String]]("url") // nullable

  def * = (id, name, url) <> (Actor.tupled, Actor.unapply)

} // Actors

object ActorsDao extends TableQuery(new Actors(_)) {
  val db = Database.forConfig("postgresdb")
  assert(db != null)
  println(">>>> open, db: " + db)

  def findById(id: Int): Future[Option[Actor]] = {
    db.run(this.filter(_.id === id).result).map(_.headOption)
  }

  def findByName(name: String): Future[Option[Actor]] = {
    db.run(this.filter(_.name === name).result).map(_.headOption)
  }

  def create(actor: Actor): Future[Actor] = {
    db.run(this returning this.map(_.id) into ((act, id) => act.copy(id = id)) += actor)
  }

  def deleteById(id: Int): Future[Int] = {
    db.run(this.filter(_.id === id).delete)
  }

  // this is the one to go with; from 'opinionated' article but with `Await.result()` added
  // (see https://sap1ens.com/blog/2015/07/26/scala-slick-3-how-to-start/)
  def list() = {
    println(">>>> list, actors:")

    println(">>>> list pre-Await")
    Await.result( //@TODO remove use of `Await`
      db.run(
        this.result.map(_.foreach {
          case actor: Actor => println(">>>> id:" + actor.id + ", name: " + actor.name + ", url: " + actor.url)
        })
      ),
      Duration.Inf)
    println(">>>> list post-Await")
  }

  /*def list2(): Future[Vector[Actor]] = {
    db.run(
      this.result
      )
  }*/

  def names() = {
    println(">>>> names: w/ Future")

    val actors = TableQuery[Actors]
    val q = for (a <- actors) yield a.name
    val a = q.result
    val f = db.run(a)
    println(">>>> names pre-f.onComplete")
    f.onComplete {
      case Success(value) => println(">>>> name"); value.foreach { println }
      case Failure(e) => e.printStackTrace
    }
    println(">>>> names post-f.onComplete")
  }

  def close() = { println(">>>> closing..."); db.close }

} // ActorsDao

// could also do
//val ActorsDao = TableQuery[Actors]