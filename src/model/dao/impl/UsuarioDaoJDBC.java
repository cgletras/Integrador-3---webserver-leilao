package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import db.DB;
import db.DbException;
import model.dao.UsuarioDao;
import model.entities.Leilao;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO Usuario (nome, email, senha, data_nascimento, ativo) " + "VALUES " + "(?, ?, ?, ?, ?)",
					java.sql.Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getEmail());
			st.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setBoolean(5, obj.isAtivo());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdUsuario(id);
				}
				DB.closeResultSet(rs);
			} 
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} 
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Usuario obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET nome= ?, email= ?, senha= ?, data_nascimento= ?, ativo= ? " + 
					"WHERE id_usuario= ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getSenha());
			st.setDate(4, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setBoolean(5, obj.isAtivo());
			st.setInt(6, obj.getIdUsuario());
						
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Usuario findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Usuario "
					+ "WHERE id_usuario = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("id_usuario"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setDataNascimento(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setAtivo(rs.getBoolean("ativo"));
				
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Usuario> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT id_usuario, nome, email, senha, data_nascimento, ativo "
					+ "FROM Usuario "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Usuario> list = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();
			
			while (rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("id_usuario"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setDataNascimento(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setAtivo(rs.getBoolean("ativo"));
						
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public Usuario findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM Usuario "
					+ "WHERE email = ?");
			
			st.setString(1, email);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Usuario obj = new Usuario();
				obj.setIdUsuario(rs.getInt("id_usuario"));
				obj.setNome(rs.getString("nome"));
				obj.setEmail(rs.getString("email"));
				obj.setSenha(rs.getString("senha"));
				obj.setDataNascimento(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
				obj.setAtivo(rs.getBoolean("ativo"));
				
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void inactivate(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET ativo= ? " + 
					"WHERE id_usuario= ?");
			
			st.setBoolean(1, false);
			st.setInt(2, id);;
									
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Usuario> findByLeilao(Leilao obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activate(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE Usuario " + 
					"SET ativo= ? " + 
					"WHERE id_usuario= ?");
			
			st.setBoolean(1, true);
			st.setInt(2, id);;
									
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
}
