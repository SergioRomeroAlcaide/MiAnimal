package Controlador;

import Modelo.Recordatorio;
import dao.RecordatorioDAO;

import java.sql.SQLException;
import java.util.List;

public class RecordatorioController {
    private final RecordatorioDAO recordatorioDAO;

    public RecordatorioController() {
        this.recordatorioDAO = new RecordatorioDAO();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) throws SQLException {
        recordatorioDAO.createRecordatorio(recordatorio);
    }

    public List<Recordatorio> obtenerRecordatorios() throws SQLException {
        return recordatorioDAO.getAllRecordatorios();
    }

    public void actualizarRecordatorio(Recordatorio recordatorio) throws SQLException {
        recordatorioDAO.updateRecordatorio(recordatorio);
    }

    public void eliminarRecordatorio(int id) throws SQLException {
        recordatorioDAO.deleteRecordatorio(id);
    }
}

