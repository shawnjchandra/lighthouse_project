CREATE SEQUENCE seq_idAgen START 1 INCREMENT 1;
CREATE SEQUENCE seq_idPel START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION pengguna_id_increment()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.tipe = 'Agen' THEN
        -- Jika tipe adalah Agen
        NEW.idAgen := nextval('seq_idAgen');
    ELSE
        -- Jika tipe adalah Pelanggan
        NEW.idPel := nextval('seq_idPel');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER pengguna_id_trigger
BEFORE INSERT ON Pengguna
FOR EACH ROW
EXECUTE FUNCTION pengguna_id_increment();


