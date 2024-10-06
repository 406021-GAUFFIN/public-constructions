package ar.edu.utn.frc.tup.lc.iv.dtos.note;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a note in the system.
 */
@Setter
@Getter
@NoArgsConstructor
public class NoteRequestDto {
	/**
	 * Description of the note.
	 */
	private String description;
}
