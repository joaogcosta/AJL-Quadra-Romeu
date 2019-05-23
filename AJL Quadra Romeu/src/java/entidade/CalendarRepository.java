/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.calendar.domain.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer>  {

	@Query(value = "select * from calendar c where date_trunc('day',c.start_time) = :date order by c.start_time desc", nativeQuery = true)
	List<Calendar> findByStartTime(@Param("date") Date startTime);

    public Calendar save(Calendar calendar);

    public Iterable<Calendar> findAll();

}
