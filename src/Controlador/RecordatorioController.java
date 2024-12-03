package Controlador;

import DAO.RecordatorioDAO;
import Modelo.Recordatorio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RecordatorioController {
    private final RecordatorioDAO recordatorioDAO;

    public RecordatorioController(Connection connection) {
        this.recordatorioDAO = new RecordatorioDAO(connection);
    }

    public List<Recordatorio> obtenerRecordatorios() throws SQLException {
        return recordatorioDAO.obtenerRecordatorios();
    }

    public void marcarRecordatorioEnviado(int recordatorioId) throws SQLException {
        recordatorioDAO.marcarRecordatorioEnviado(recordatorioId);
    }
}
