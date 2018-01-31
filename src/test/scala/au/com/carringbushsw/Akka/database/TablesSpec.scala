/**
  * Created by psc on 9/11/17.
  */

package au.com.carringbushsw.Akka.database

import org.scalatest.FlatSpec
import slick.jdbc.PostgresProfile.api._
import scala.util.{ Try, Success, Failure  }
import scala.concurrent.ExecutionContext.Implicits.global

class TablesSpec extends FlatSpec {

  // note: this is testing real tables in a real database
  "Tables" should "return rows" in {
    try {
      ActorsDao.list
      ActorsDao.names
      assert(true)
    } catch {
      case t: Throwable => assert(false)
    }
  }

  it should "return an actor by id" in {
    val fById1 = ActorsDao.findById(1)
    fById1.onComplete {
      case Success(value) => value match {
        case Some(actor) => assert(actor.name == "Requester")
        case None => assert(false)
      }
      case Failure(e) => e.printStackTrace; assert(false)
    }
  }

  it should "return an actor by name" in {
    val fByRequester = ActorsDao.findByName("Requester")
    fByRequester.onComplete {
      case Success(value) => value match {
        case Some(actor) => assert(actor.name == "Requester")
        case None => assert(false)
      }
      case Failure(e) => e.printStackTrace; assert(false)
    }
  }

} // TablesSpec
