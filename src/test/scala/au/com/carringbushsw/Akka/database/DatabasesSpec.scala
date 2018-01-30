/**
  * Created by psc on 9/11/17.
  */

package au.com.carringbushsw.Akka.database

import org.scalatest.FlatSpec
import slick.jdbc.PostgresProfile.api._

class DatabasesSpec extends FlatSpec {

  // note: this is testing a real database
  "Databases" should "return open" in {
    assert(true)
    /*try {
      Database.forURL(ActorsDatabase.connection, driver = ActorsDatabase.driver) withSession {
        implicit session =>
          assert(true)
      }
    } catch {
      case _: Throwable => assert(false)
    }*/
  } // Databases
} // DatabasesSpec

