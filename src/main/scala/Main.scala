/**
  * Created by psc on 9/11/17.
  */

import slick.jdbc.PostgresProfile.api._
import au.com.carringbushsw.Akka.database.{ Actor, Actors, ActorsDao }
import scala.util.{ Try, Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

object Main {
  def main(args: Array[String]): Unit = {

    try {    
      println(">>>> about to list...")
      ActorsDao.list
      /*println(">>>> about to list2...")
      val fList2 = ActorsDao.list2
      fList2.onComplete {
        case Success(value) => value.foreach {
          case actor: Actor => println(">>>> id:" + actor.id + ", name: " + actor.name + ", url: " + actor.url)
        }
        case Failure(e) => e.printStackTrace
      }*/
      ActorsDao.names

      ActorsDao.filter(_.id === 1)         // 1
      ActorsDao.filter(_.id === 2).result  // 2

      // variations on an `Option` retrieval theme
      val fById1 = ActorsDao.findById(1)
      fById1.onComplete {
        case Success(value) => println(s">>>> findById(1): ${value.getOrElse("(not an actor)")}")
        case Failure(e) => e.printStackTrace
      }

      val fById2 = ActorsDao.findById(2)
      fById2.onComplete {
        case Success(value) => value match {
          case Some(value) => println(s">>>> findById(2): $value")
          case None => println("(no actor)")
        }
        case Failure(e) => e.printStackTrace
      }

      val fByRequester = ActorsDao.findByName("Requester")
      fByRequester.onComplete {
        case Success(value) => println(s">>>> findByName(Requester): ${value.getOrElse("(not an actor)")}")
        case Failure(e) => e.printStackTrace
      }

      val fByResponder = ActorsDao.findByName("Responder")
      fByResponder.onComplete {
        case Success(value) => value match {
          case Some(value) => println(s">>>> findByName(Responder): $value")
          case None => println("no actor")
        }
        case Failure(e) => e.printStackTrace
      }
    } finally ActorsDao.close

  } // main
} // Main