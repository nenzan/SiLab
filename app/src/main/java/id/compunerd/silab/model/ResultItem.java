package id.compunerd.silab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItem {

    @SerializedName("id_pengujian")
    @Expose
    private String idPengujian;
    @SerializedName("id_petugas_admin")
    @Expose
    private Object idPetugasAdmin;
    @SerializedName("id_petugas_lab")
    @Expose
    private Object idPetugasLab;
    @SerializedName("id_perusahaan")
    @Expose
    private String idPerusahaan;
    @SerializedName("id_barang")
    @Expose
    private String idBarang;
    @SerializedName("jumlah_barang")
    @Expose
    private String jumlahBarang;
    @SerializedName("total_harga")
    @Expose
    private String totalHarga;
    @SerializedName("tgl_bayar")
    @Expose
    private Object tglBayar;
    @SerializedName("tgl_verifikasi")
    @Expose
    private Object tglVerifikasi;
    @SerializedName("tgl_barang_diterima")
    @Expose
    private Object tglBarangDiterima;
    @SerializedName("tgl_barang_selesai")
    @Expose
    private Object tglBarangSelesai;
    @SerializedName("bukti_pembayaran")
    @Expose
    private Object buktiPembayaran;
    @SerializedName("status_pengujian")
    @Expose
    private String statusPengujian;
    @SerializedName("hasil_pengujian")
    @Expose
    private Object hasilPengujian;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("nama_lab")
    @Expose
    private String namaLab;

    public String getIdPengujian() {
        return idPengujian;
    }

    public void setIdPengujian(String idPengujian) {
        this.idPengujian = idPengujian;
    }

    public Object getIdPetugasAdmin() {
        return idPetugasAdmin;
    }

    public void setIdPetugasAdmin(Object idPetugasAdmin) {
        this.idPetugasAdmin = idPetugasAdmin;
    }

    public Object getIdPetugasLab() {
        return idPetugasLab;
    }

    public void setIdPetugasLab(Object idPetugasLab) {
        this.idPetugasLab = idPetugasLab;
    }

    public String getIdPerusahaan() {
        return idPerusahaan;
    }

    public void setIdPerusahaan(String idPerusahaan) {
        this.idPerusahaan = idPerusahaan;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(String jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public Object getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(Object tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Object getTglVerifikasi() {
        return tglVerifikasi;
    }

    public void setTglVerifikasi(Object tglVerifikasi) {
        this.tglVerifikasi = tglVerifikasi;
    }

    public Object getTglBarangDiterima() {
        return tglBarangDiterima;
    }

    public void setTglBarangDiterima(Object tglBarangDiterima) {
        this.tglBarangDiterima = tglBarangDiterima;
    }

    public Object getTglBarangSelesai() {
        return tglBarangSelesai;
    }

    public void setTglBarangSelesai(Object tglBarangSelesai) {
        this.tglBarangSelesai = tglBarangSelesai;
    }

    public Object getBuktiPembayaran() {
        return buktiPembayaran;
    }

    public void setBuktiPembayaran(Object buktiPembayaran) {
        this.buktiPembayaran = buktiPembayaran;
    }

    public String getStatusPengujian() {
        return statusPengujian;
    }

    public void setStatusPengujian(String statusPengujian) {
        this.statusPengujian = statusPengujian;
    }

    public Object getHasilPengujian() {
        return hasilPengujian;
    }

    public void setHasilPengujian(Object hasilPengujian) {
        this.hasilPengujian = hasilPengujian;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getNamaLab() {
        return namaLab;
    }

    public void setNamaLab(String namaLab) {
        this.namaLab = namaLab;
    }

}