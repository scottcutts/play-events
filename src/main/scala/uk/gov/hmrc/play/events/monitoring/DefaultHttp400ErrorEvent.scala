/*
 * Copyright 2015 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.play.events.monitoring

import uk.gov.hmrc.play.events.AlertLevel._
import uk.gov.hmrc.play.events.monitoring.HttpMonitor._
import uk.gov.hmrc.play.events.{Measurable, Alertable}
import uk.gov.hmrc.play.http.Upstream4xxResponse

case class DefaultHttp400ErrorEvent(source: String,
                                    name: String,
                                    level: AlertLevel,
                                    alertCode: Option[AlertCode],
                                    data: Map[String, String]) extends Alertable with Measurable

object DefaultHttp400ErrorEvent {
  def apply(source: String, response: Upstream4xxResponse, alertCode: Option[AlertCode]) = new DefaultHttp400ErrorEvent(
    source = source,
    name = "Http400Error",
    level = MAJOR,
    alertCode = alertCode,
    data = Map (
      "Error Message" -> response.message,
      "Code" -> response.upstreamResponseCode.toString,
      "Report As" -> response.reportAs.toString
    )
  )

}
