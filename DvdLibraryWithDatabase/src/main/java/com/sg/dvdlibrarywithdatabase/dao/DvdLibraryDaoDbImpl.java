/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrarywithdatabase.dao;

import com.sg.dvdlibrarywithdatabase.model.Dvd;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author wgv85
 */
public class DvdLibraryDaoDbImpl implements DvdLibraryDao {
    
    // PREPARED STATEMENTS
    private static final String SQL_INSERT_DVD
            = "insert into dvd "
            + "(title, releaseDate, directorName, studio, mpaaRating, userRating) "
            + "values (?, ?, ?, ?, ?, ?) ";
    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvdId = ? ";
    private static final String SQL_SELECT_DVD
            = "select * from dvd where dvdId = ? ";
    private static final String SQL_UPDATE_DVD
            = "update dvd set "
            + "title = ?, releaseDate = ?, directorName = ?, studio = ?, mpaaRating = ?, userRating = ? "
            + "where dvdvId = ? ";
    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvd ";
    private static final String SQL_SELECT_DVD_BY_MPAA
            = "select * from dvd where mpaaRating = ? ";
    private static final String SQL_SELECT_DVD_BY_STUDIO
            = "select * from dvd where studio = ?";
    private static final String SQL_SELECT_DVD_BY_DIRECTOR_NAME
            = "select * from dvd where directorName = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate jdbcTemplate;

    @Override
    public Dvd addDvd(Dvd dvd) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd getDvdById(int dvdId) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeDvdLibrary(int dvdId) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editDvdLibrary(Dvd dvd) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getDvdByMpaa(String mpaaRating) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getDvdByStudio(String studio) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dvd> getDvdByDirectorName(String directorName) throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
