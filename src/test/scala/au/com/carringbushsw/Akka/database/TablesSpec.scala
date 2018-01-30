/**
  * Created by psc on 9/11/17.
  */

package au.com.carringbushsw.Akka.database

import org.scalatest.FlatSpec
import slick.jdbc.PostgresProfile.api._

class TablesSpec extends FlatSpec {

  // note: this is testing real tables in a real database
  "Tables" should "return something" in {
    assert(true)
    /*try {
      Database.forURL(ActorsDatabase.connection, driver = ActorsDatabase.driver) withSession {
        implicit session =>
          val actors = TableQuery[Actors]
          var i = 0

          actors.list foreach { row =>
            i += 1
            assert(row._1 == i)
            println("1: " + row._1 + ", 2: " + row._2 + ", 3: " + row._3)
          }
          assert(true)
      }
    } catch {
      case _: Throwable => assert(false)
    }*/
  }
} // TablesSpec
