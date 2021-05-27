-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Bulan Mei 2021 pada 01.20
-- Versi server: 10.1.38-MariaDB
-- Versi PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `done_list_app`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `activity`
--

CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `activity_name` varchar(30) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `activity`
--

INSERT INTO `activity` (`id`, `activity_name`, `createdAt`, `updatedAt`, `user_id`) VALUES
(7, 'Play NiKI SOngs', '2021-05-26 16:43:28', '2021-05-26 16:43:28', 7),
(8, 'Code', '2021-05-26 16:43:36', '2021-05-26 16:43:36', 7),
(9, 'ElonMusk Wannabe', '2021-05-26 16:43:44', '2021-05-26 16:43:44', 7),
(10, 'Game', '2021-05-27 20:58:27', '2021-05-27 20:58:27', 7),
(13, 'Sing along', '2021-05-27 21:03:43', '2021-05-27 21:03:43', 7),
(15, 'dreaming', '2021-05-27 21:06:32', '2021-05-27 21:06:32', 7),
(16, 'ding Activitiy', '2021-05-27 21:07:14', '2021-05-27 21:07:14', 7),
(17, 'uyee', '2021-05-27 21:07:43', '2021-05-27 21:07:43', 7),
(18, 'drifting', '2021-05-27 21:25:01', '2021-05-27 21:25:01', 8),
(19, 'tes Chek', '2021-05-27 22:26:31', '2021-05-27 22:26:31', 7),
(20, 'bruuh', '2021-05-27 23:20:16', '2021-05-27 23:20:16', 9);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nama` varchar(26) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `nama`, `email`, `password`, `createdAt`, `updatedAt`) VALUES
(7, 'Adnar', 'a@a.com', '$2a$08$O1OG/OKT2ZNzAJTGMDS.ROG/DNK3Npuks7ld6SjjMOUsacDR0g5nm', '2021-05-26 16:29:29', '2021-05-26 16:29:29'),
(8, 'Muhamad Febri', 'algani@gmail.com', '$2a$08$35fC1v.4FVcfMf3d63XSfOI.p53FztKNSFY1w.qY7k.yLUyG9A1cy', '2021-05-27 21:24:44', '2021-05-27 21:24:44'),
(9, 'Last Chekup', 'lc@gmail.com', '$2a$08$FLgDY7KP2vUzM7u.mBH0weoSjjnpo51wCsPVpuxTLSSPYyJxVe4Hu', '2021-05-27 23:20:07', '2021-05-27 23:20:07');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `activity_name` (`activity_name`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `activity`
--
ALTER TABLE `activity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
