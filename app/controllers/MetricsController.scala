package controllers

import java.util.concurrent.TimeUnit

import com.codahale.metrics._
import com.google.inject.Inject
import play.api.mvc.{ Action, Controller }

import scala.collection.JavaConversions._

class MetricsController @Inject() (metricRegistry: MetricRegistry) extends Controller {

  def metrics() = Action { request =>
    Ok(views.html.Metrics.metrics(Metrics(metricRegistry)))
  }

}

case class Metrics(counters: Map[String, Counter], timers: Map[String, Timer], meters: Map[String, Meter], histograms: Map[String, Histogram])

object Metrics {
  def apply(metricRegistry: MetricRegistry): Metrics = {
    Metrics(metricRegistry.getCounters.toMap, metricRegistry.getTimers.toMap, metricRegistry.getMeters.toMap, metricRegistry.getHistograms.toMap)
  }
}

