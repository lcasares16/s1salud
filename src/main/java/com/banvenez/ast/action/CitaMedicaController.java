package com.banvenez.ast.action;
import com.banvenez.ast.dto.citas.*;
import com.banvenez.ast.util.ConnectionUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/S1Salud/citas-medicas")
@CrossOrigin
public class CitaMedicaController {

    @PostMapping("/especialidades")
    public ResponseEntity<?> crearEspecialidad(@RequestBody EspecialidadDto especialidadDto) {
        if (especialidadDto == null || especialidadDto.getNombre() == null || especialidadDto.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la especialidad es obligatorio.");
        }
        ConnectionUtil db = new ConnectionUtil();
        Integer nuevaEspecialidadId = db.crearEspecialidad(especialidadDto);
        if (nuevaEspecialidadId != null) {
            especialidadDto.setEspecialidadId(nuevaEspecialidadId);
            return ResponseEntity.status(HttpStatus.CREATED).body(especialidadDto);
        } else {
            // Check if it was a duplicate or other error
            // This basic check might need refinement based on how crearEspecialidad signals specific errors
            EspecialidadDto existing = db.obtenerEspecialidades().stream()
                    .filter(e -> e.getNombre().equalsIgnoreCase(especialidadDto.getNombre()))
                    .findFirst().orElse(null);
            if (existing != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: La especialidad con el nombre '" + especialidadDto.getNombre() + "' ya existe.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la especialidad.");
        }
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<EspecialidadDto>> obtenerEspecialidades() {
        ConnectionUtil db = new ConnectionUtil();
        List<EspecialidadDto> especialidades = db.obtenerEspecialidades();
        return ResponseEntity.ok(especialidades);
    }

    @GetMapping("/especialidades/{id}")
    public ResponseEntity<?> obtenerEspecialidadPorId(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID de la especialidad no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        EspecialidadDto especialidad = db.obtenerEspecialidadPorId(id);
        if (especialidad != null) {
            return ResponseEntity.ok(especialidad);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada con ID: " + id);
        }
    }

    @PutMapping("/especialidades/{id}")
    public ResponseEntity<String> actualizarEspecialidad(@PathVariable Integer id, @RequestBody EspecialidadDto especialidadDto) {
        if (id == null || especialidadDto == null) {
            return ResponseEntity.badRequest().body("ID de especialidad y datos son obligatorios.");
        }
        if (especialidadDto.getNombre() == null || especialidadDto.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de la especialidad es obligatorio.");
        }

        ConnectionUtil db = new ConnectionUtil();
        // Ensure the ID in the DTO matches the path variable for consistency
        especialidadDto.setEspecialidadId(id);
        String resultado = db.actualizarEspecialidad(especialidadDto);

        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Especialidad actualizada correctamente.");
        } else if (resultado != null && resultado.contains("Nombre duplicado")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resultado);
        } else if (resultado != null && resultado.contains("Especialidad no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la especialidad: " + resultado);
        }
    }

    @DeleteMapping("/especialidades/{id}")
    public ResponseEntity<String> eliminarEspecialidad(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID de la especialidad no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        String resultado = db.eliminarEspecialidad(id);

        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Especialidad eliminada correctamente.");
        } else if (resultado != null && resultado.contains("Especialidad no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else if (resultado != null && resultado.toUpperCase().contains("FOREIGN KEY VIOLATION")) { // Basic check for FK violation
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: No se puede eliminar la especialidad, está siendo utilizada por uno o más médicos.");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la especialidad: " + resultado);
        }
    }

    // Endpoints para Pacientes, Medicos, Citas will be added here later

    // --- Endpoints para Pacientes ---

    @PostMapping("/pacientes")
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDto pacienteDto) {
        if (pacienteDto == null || pacienteDto.getCedula() == null || pacienteDto.getCedula().trim().isEmpty() ||
                pacienteDto.getNombre() == null || pacienteDto.getNombre().trim().isEmpty() ||
                pacienteDto.getApellido() == null || pacienteDto.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Cédula, nombre y apellido del paciente son obligatorios.");
        }
        ConnectionUtil db = new ConnectionUtil();
        Integer nuevoPacienteId = db.crearPaciente(pacienteDto);

        if (nuevoPacienteId != null) {
            pacienteDto.setPacienteId(nuevoPacienteId);
            // Optionally re-fetch to get default values like fecha_registro
            PacienteDto fetchedPaciente = db.obtenerPacientePorId(nuevoPacienteId);
            return ResponseEntity.status(HttpStatus.CREATED).body(fetchedPaciente != null ? fetchedPaciente : pacienteDto);
        } else {
            PacienteDto existing = db.obtenerPacientePorCedula(pacienteDto.getCedula());
            if (existing != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: El paciente con la cédula '" + pacienteDto.getCedula() + "' ya existe.");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el paciente.");
        }
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<PacienteDto>> obtenerPacientes() {
        ConnectionUtil db = new ConnectionUtil();
        List<PacienteDto> pacientes = db.obtenerPacientes();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<?> obtenerPacientePorId(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID del paciente no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        PacienteDto paciente = db.obtenerPacientePorId(id);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con ID: " + id);
        }
    }

    @GetMapping("/pacientes/cedula/{cedula}")
    public ResponseEntity<?> obtenerPacientePorCedula(@PathVariable String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La cédula del paciente no puede ser nula o vacía.");
        }
        ConnectionUtil db = new ConnectionUtil();
        PacienteDto paciente = db.obtenerPacientePorCedula(cedula);
        if (paciente != null) {
            return ResponseEntity.ok(paciente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con cédula: " + cedula);
        }
    }

    @PutMapping("/pacientes/{id}")
    public ResponseEntity<String> actualizarPaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto) {
        if (id == null || pacienteDto == null) {
            return ResponseEntity.badRequest().body("ID de paciente y datos son obligatorios.");
        }
        if (pacienteDto.getCedula() == null || pacienteDto.getCedula().trim().isEmpty() ||
                pacienteDto.getNombre() == null || pacienteDto.getNombre().trim().isEmpty() ||
                pacienteDto.getApellido() == null || pacienteDto.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Cédula, nombre y apellido del paciente son obligatorios.");
        }

        ConnectionUtil db = new ConnectionUtil();
        // Ensure ID from path is used
        pacienteDto.setPacienteId(id);
        String resultado = db.actualizarPaciente(pacienteDto);

        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Paciente actualizado correctamente.");
        } else if (resultado != null && resultado.contains("Cédula duplicada")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resultado);
        } else if (resultado != null && resultado.contains("Paciente no encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el paciente: " + resultado);
        }
    }

    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID del paciente no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        String resultado = db.eliminarPaciente(id);

        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Paciente eliminado correctamente.");
        } else if (resultado != null && resultado.contains("Paciente no encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else if (resultado != null && (resultado.toUpperCase().contains("FOREIGN KEY VIOLATION") || resultado.contains("tiene citas asociadas"))) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: No se puede eliminar el paciente, tiene citas asociadas.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el paciente: " + resultado);
        }
    }

    // --- Endpoints para Médicos ---

    @PostMapping("/medicos")
    public ResponseEntity<?> crearMedico(@RequestBody MedicosDto medicoDto) {
        if (medicoDto == null || medicoDto.getCedula() == null || medicoDto.getCedula().trim().isEmpty() ||
                medicoDto.getNombre() == null || medicoDto.getNombre().trim().isEmpty() ||
                medicoDto.getApellido() == null || medicoDto.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Cédula, nombre y apellido del médico son obligatorios.");
        }
        // especialidadId is optional at DTO level, SP handles null.

        ConnectionUtil db = new ConnectionUtil();
        Integer nuevoMedicoId = db.crearMedico(medicoDto);

        if (nuevoMedicoId != null) {
            medicoDto.setMedicoId(nuevoMedicoId);
            // Optionally re-fetch to get default values like fecha_registro and to confirm especialidadId linkage
            MedicosDto fetchedMedico = db.obtenerMedicoPorId(nuevoMedicoId);
            if (fetchedMedico != null) {
                // If nombreEspecialidad is not populated by SP, we might need to fetch it here
                // For now, assume SP might be enhanced or it's handled client-side/later
                if (fetchedMedico.getEspecialidadId() != null && fetchedMedico.getNombreEspecialidad() == null) {
                    EspecialidadDto especialidad = db.obtenerEspecialidadPorId(fetchedMedico.getEspecialidadId());
                    if (especialidad != null) {
                        fetchedMedico.setNombreEspecialidad(especialidad.getNombre());
                    }
                }
                return ResponseEntity.status(HttpStatus.CREATED).body(fetchedMedico);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoDto);
        } else {
            // Attempt to check for duplicate cedula if creation failed
            // This is a basic check, more robust error reporting from ConnectionUtil would be better
            List<MedicosDto> existingMedicos = db.obtenerMedicos(); // Less efficient, better to have a getByCedula for Medico
            boolean cedulaExists = existingMedicos.stream().anyMatch(m -> m.getCedula().equals(medicoDto.getCedula()));
            if (cedulaExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: El médico con la cédula '" + medicoDto.getCedula() + "' ya existe.");
            }
            if (medicoDto.getEspecialidadId() != null) {
                EspecialidadDto especialidad = db.obtenerEspecialidadPorId(medicoDto.getEspecialidadId());
                if (especialidad == null) {
                    return ResponseEntity.badRequest().body("Error: La especialidad_id " + medicoDto.getEspecialidadId() + " no existe.");
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el médico. Verifique los datos, especialmente la especialidad ID.");
        }
    }

    @GetMapping("/medicos")
    public ResponseEntity<List<MedicosDto>> obtenerMedicos() {
        ConnectionUtil db = new ConnectionUtil();
        List<MedicosDto> medicos = db.obtenerMedicos();
        // Populate nombreEspecialidad for each medico
        for (MedicosDto medico : medicos) {
            if (medico.getEspecialidadId() != null && medico.getNombreEspecialidad() == null) {
                EspecialidadDto especialidad = db.obtenerEspecialidadPorId(medico.getEspecialidadId());
                if (especialidad != null) {
                    medico.setNombreEspecialidad(especialidad.getNombre());
                }
            }
        }
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/medicos/{id}")
    public ResponseEntity<?> obtenerMedicoPorId(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID del médico no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        MedicosDto medico = db.obtenerMedicoPorId(id);
        if (medico != null) {
            if (medico.getEspecialidadId() != null && medico.getNombreEspecialidad() == null) {
                EspecialidadDto especialidad = db.obtenerEspecialidadPorId(medico.getEspecialidadId());
                if (especialidad != null) {
                    medico.setNombreEspecialidad(especialidad.getNombre());
                }
            }
            return ResponseEntity.ok(medico);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Médico no encontrado con ID: " + id);
        }
    }

    @GetMapping("/medicos/especialidad/{especialidadId}")
    public ResponseEntity<?> obtenerMedicosPorEspecialidad(@PathVariable Integer especialidadId) {
        if (especialidadId == null) {
            return ResponseEntity.badRequest().body("El ID de la especialidad no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        // First check if especialidad exists
        EspecialidadDto especialidad = db.obtenerEspecialidadPorId(especialidadId);
        if (especialidad == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada con ID: " + especialidadId);
        }

        List<MedicosDto> medicos = db.obtenerMedicosPorEspecialidad(especialidadId);
        for (MedicosDto medico : medicos) {
            // Set nombreEspecialidad since we already fetched it
            medico.setNombreEspecialidad(especialidad.getNombre());
        }
        return ResponseEntity.ok(medicos);
    }

    @PutMapping("/medicos/{id}")
    public ResponseEntity<String> actualizarMedico(@PathVariable Integer id, @RequestBody MedicosDto medicoDto) {
        if (id == null || medicoDto == null) {
            return ResponseEntity.badRequest().body("ID de médico y datos son obligatorios.");
        }
        if (medicoDto.getCedula() == null || medicoDto.getCedula().trim().isEmpty() ||
                medicoDto.getNombre() == null || medicoDto.getNombre().trim().isEmpty() ||
                medicoDto.getApellido() == null || medicoDto.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Cédula, nombre y apellido del médico son obligatorios.");
        }

        ConnectionUtil db = new ConnectionUtil();

        // Check if especialidad_id is valid before attempting update if provided
        if (medicoDto.getEspecialidadId() != null) {
            EspecialidadDto especialidad = db.obtenerEspecialidadPorId(medicoDto.getEspecialidadId());
            if (especialidad == null) {
                return ResponseEntity.badRequest().body("Error: La especialidad_id " + medicoDto.getEspecialidadId() + " no existe.");
            }
        }

        medicoDto.setMedicoId(id); // Ensure DTO has the correct ID from path
        String resultado = db.actualizarMedico(medicoDto);

        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Médico actualizado correctamente.");
        } else if (resultado != null && resultado.contains("Cédula duplicada")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(resultado);
        } else if (resultado != null && resultado.contains("Médico no encontrado")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else if (resultado != null && resultado.contains("Especialidad ID no válida")) {
            return ResponseEntity.badRequest().body(resultado);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el médico: " + resultado);
        }
    }

    // --- Endpoints para Citas ---

    @PostMapping("/citas")
    public ResponseEntity<?> crearCita(@RequestBody CrearCitaRequestDto citaRequestDto) {
        if (citaRequestDto == null || citaRequestDto.getPacienteId() == null ||
                citaRequestDto.getMedicoId() == null || citaRequestDto.getFechaHora() == null) {
            return ResponseEntity.badRequest().body("Paciente ID, Médico ID y Fecha/Hora son obligatorios para crear una cita.");
        }

        ConnectionUtil db = new ConnectionUtil();

        // Validate Paciente existence
        if (db.obtenerPacientePorId(citaRequestDto.getPacienteId()) == null) {
            return ResponseEntity.badRequest().body("Error: El paciente con ID " + citaRequestDto.getPacienteId() + " no existe.");
        }
        // Validate Medico existence
        if (db.obtenerMedicoPorId(citaRequestDto.getMedicoId()) == null) {
            return ResponseEntity.badRequest().body("Error: El médico con ID " + citaRequestDto.getMedicoId() + " no existe.");
        }

        Integer nuevaCitaId = db.crearCita(citaRequestDto);

        if (nuevaCitaId != null) {
            CitaDto nuevaCita = db.obtenerCitaPorId(nuevaCitaId); // Fetch the full CitaDto
            if (nuevaCita != null) {
                // Populate additional details for the response
                populateCitaDtoDetails(nuevaCita, db);
                return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCita);
            }
            // Fallback if fetching fails, though ideally it shouldn't
            return ResponseEntity.status(HttpStatus.CREATED).body("Cita creada con ID: " + nuevaCitaId + ". No se pudieron obtener todos los detalles.");
        } else {
            // This could be due to FK violation if IDs were checked but deleted just before, or other DB errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la cita. Verifique que el Paciente y Médico existan.");
        }
    }

    @GetMapping("/citas/{id}")
    public ResponseEntity<?> obtenerCitaPorId(@PathVariable Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID de la cita no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        CitaDto cita = db.obtenerCitaPorId(id);
        if (cita != null) {
            populateCitaDtoDetails(cita, db);
            return ResponseEntity.ok(cita);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada con ID: " + id);
        }
    }

    @GetMapping("/citas/paciente/{pacienteId}")
    public ResponseEntity<?> obtenerCitasPorPaciente(
            @PathVariable Integer pacienteId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fechaHasta) {
        if (pacienteId == null) {
            return ResponseEntity.badRequest().body("El ID del paciente no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        List<CitaDto> citas = db.obtenerCitasPorPaciente(pacienteId, fechaDesde, fechaHasta);
        for (CitaDto cita : citas) {
            populateCitaDtoDetails(cita, db);
        }
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/citas/medico/{medicoId}")
    public ResponseEntity<?> obtenerCitasPorMedico(
            @PathVariable Integer medicoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date fechaHasta) {
        if (medicoId == null) {
            return ResponseEntity.badRequest().body("El ID del médico no puede ser nulo.");
        }
        ConnectionUtil db = new ConnectionUtil();
        List<CitaDto> citas = db.obtenerCitasPorMedico(medicoId, fechaDesde, fechaHasta);
        for (CitaDto cita : citas) {
            populateCitaDtoDetails(cita, db);
        }
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/citas/{id}/estado")
    public ResponseEntity<String> actualizarEstadoCita(@PathVariable Integer id,
                                                       @RequestBody Map<String, String> payload) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID de la cita no puede ser nulo.");
        }
        String nuevoEstado = payload.get("estado");
        String notasMedico = payload.get("notasMedico");

        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El nuevo estado es obligatorio.");
        }

        ConnectionUtil db = new ConnectionUtil();
        CitaDto citaExistente = db.obtenerCitaPorId(id);
        if (citaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada con ID: " + id);
        }

        String resultado = db.actualizarEstadoCita(id, nuevoEstado, notasMedico);
        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Estado de la cita actualizado correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar estado de la cita: " + resultado);
        }
    }

    @PutMapping("/citas/{id}/reprogramar")
    public ResponseEntity<String> reprogramarCita(@PathVariable Integer id,
                                                  @RequestBody Map<String, Object> payload) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID de la cita no puede ser nulo.");
        }

        Object fechaHoraObj = payload.get("fechaHora");
        String notasPaciente = (String) payload.get("notasPaciente"); // Can be null

        if (!(fechaHoraObj instanceof String)) {
            return ResponseEntity.badRequest().body("fechaHora debe ser una cadena en formato ISO DateTime (YYYY-MM-DDTHH:mm:ss.sssZ).");
        }

        Date nuevaFechaHora;
        try {
            // Attempt to parse the date string.
            // Using SimpleDateFormat as javax.xml.bind is not always available.
            nuevaFechaHora = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse((String)fechaHoraObj);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de fechaHora inválido. Use ISO DateTime (e.g., YYYY-MM-DDTHH:mm:ss.SSSXXX). Error: " + e.getMessage());
        }


        ConnectionUtil db = new ConnectionUtil();
        CitaDto citaExistente = db.obtenerCitaPorId(id);
        if (citaExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada con ID: " + id);
        }

        String resultado = db.reprogramarCita(id, nuevaFechaHora, notasPaciente);
        if ("SUCCESS".equals(resultado)) {
            return ResponseEntity.ok("Cita reprogramada correctamente.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al reprogramar la cita: " + resultado);
        }
    }

    // Helper method to populate CitaDto with details from related entities
    private void populateCitaDtoDetails(CitaDto cita, ConnectionUtil db) {
        if (cita == null) return;

        if (cita.getPacienteId() != null) {
            PacienteDto paciente = db.obtenerPacientePorId(cita.getPacienteId());
            if (paciente != null) {
                cita.setPacienteNombreCompleto(paciente.getNombre() + " " + paciente.getApellido());
            }
        }

        if (cita.getMedicoId() != null) {
            MedicosDto medico = db.obtenerMedicoPorId(cita.getMedicoId());
            if (medico != null) {
                cita.setMedicoNombreCompleto(medico.getNombre() + " " + medico.getApellido());
                if (medico.getEspecialidadId() != null) {
                    EspecialidadDto especialidad = db.obtenerEspecialidadPorId(medico.getEspecialidadId());
                    if (especialidad != null) {
                        cita.setMedicoEspecialidad(especialidad.getNombre());
                    }
                }
            }
        }
    }


}
