/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/calendar")
public class CalendarResource {
	
	@Autowired
	private CalendarService calendarService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> list()
	{
		List<Calendar> calendar = calendarService.list();
		
		List<CalendarDTO> dto = calendar.stream().map(x -> new CalendarDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(value="/find", method = RequestMethod.GET)
	public ResponseEntity<List<CalendarDTO>> findList(@RequestParam(value="startTime")  @DateTimeFormat(pattern="yyyy-MM-dd") Date startTime)
	{
		List<Calendar> calendar = calendarService.findList(startTime);
		
		List<CalendarDTO> dto = calendar.stream().map(x -> new CalendarDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(dto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody Calendar calendar)
	{
		calendar = calendarService.create(calendar);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(calendar.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
