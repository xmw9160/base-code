package com.xmw.lambda.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import lombok.Data;

/**
 * TestStream
 *
 * @author mingwei.xia
 * @date 2018/11/6 17:03
 * @since V1.0
 */
public class TestStream {
    public static void main(String[] args) {
        List<String> collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase)
                .collect(toList());

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(Collection::stream)
                .collect(toList());

//        Track shortestTrack = tracks.stream()
//                .min(Comparator.comparing(track -> track.getLength()))
//                .get();

        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        System.out.println(count);

//        public Set<String> findLongTracks(List<Album> albums) {
//            Set<String> trackNames = new HashSet<>();
//            for(Album album : albums) {
//                for (Track track : album.getTrackList()) {
//                    if (track.getLength() > 60) {
//                        String name = track.getName();
//                        trackNames.add(name);
//                    }
//                }
//            }
//            return trackNames;
//        }

        // flatMap 操作，把多个Stream 合并成一个 Stream 并返回
//        public Set<String> findLongTracks(List<Album> albums) {
//            Set<String> trackNames = new HashSet<>();
//            albums.stream()
//                    .flatMap(album -> album.getTracks())
//                    .filter(track -> track.getLength() > 60)
//                    .map(track -> track.getName())
//                    .forEach(name -> trackNames.add(name));
//            return trackNames;
//        }

//        return albums.stream()
//                .flatMap(album -> album.getTracks())
//                .filter(track -> track.getLength() > 60)
//                .map(track -> track.getName())
//                .collect(toSet());

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//        List<Integer> sameOrder = numbers.stream()
//                .collect(toList());
//        Assert.assertEquals(numbers, sameOrder);

//        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 2, 1));
//        List<Integer> sameOrder = numbers.stream()
//                .sorted()
//                .collect(toList());
//        System.out.println(sameOrder);

//        stream.collect(toCollection(TreeSet::new));

//        可以利用收集器让流生成一个值。 maxBy 和 minBy 允许用户按某种特定的顺序生成一个值。
//        Function<Artist,Long> getCount = artist -> artist.getMembers().count();
//        return artists.collect(maxBy(comparing(getCount)));

//        收集器 partitioningBy ，它接受一个流，并将其分成两部分

        // 数据分块
//        public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
//            return artists.collect(partitioningBy(artist -> artist.isSolo()));
//        }

        // 数据分组
//        albums.collect(groupingBy(album -> album.getMainMusician()));

        // 字符串
//        artists.stream().map(Artist::getName).collect(Collectors.joining(", ", "[", "]"));

        //组合收集器
//        Map<Artist, List<Album>> albumsByArtist
//                = albums.collect(groupingBy(album -> album.getMainMusician()));
//        Map<Artist, Integer> numberOfAlbums = new HashMap<>();
//        for(Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
//            numberOfAlbums.put(entry.getKey(), entry.getValue().size());
//        }
        // albums.collect(groupingBy(album -> album.getMainMusician(), counting()));

//        Map<Artist, List<Album>> albumsByArtist =
//                albums.collect(groupingBy(album ->album.getMainMusician()));
//        Map<Artist, List<String>> nameOfAlbums = new HashMap<>();
//        for(Entry<Artist, List<Album>> entry : albumsByArtist.entrySet()) {
//            nameOfAlbums.put(entry.getKey(), entry.getValue()
//                    .stream()
//                    .map(Album::getName)
//                    .collect(toList()));
//        }
//        return nameOfAlbums;
        // 下游收集器 mapping(Album::getName, toList()))
        // 收集器是生成最终结果的一剂配方，下游收集器则是生成部分结果的配方，主收集器中会用到下游收集器
        // albums.collect(groupingBy(Album::getMainMusician, mapping(Album::getName, toList())));

        // 一些细节
        // Java 8引入了一个新方法computeIfAbsent，该方法接受一个Lambda表达式，值不存在时使用该Lambda表达式计算新值
        // artistCache.computeIfAbsent(name, this::readArtistFromDB);

        //XXX 数据并行化
        // 串行化计算专辑曲目长度
//        albums.stream()
//                .flatMap(Album::getTracks)
//                .mapToInt(Track::getLength)
//                .sum();
        // 并行化计算专辑曲目长度
//        albums.parallelStream()
//                .flatMap(Album::getTracks)
//                .mapToInt(Track::getLength)
//                .sum();

//        ThreadLocal<Album> thisAlbum = new ThreadLocal<Album> () {
//            @Override protected Album initialValue() {
//                return database.lookupCurrentAlbum();
//            }
//        };
//        ThreadLocal.withInitial(() -> database.lookupCurrentAlbum());

//        Set<String> nationalities
//                = album.getMusicians()
//                .filter(artist -> artist.getName().startsWith("The"))
//                .map(artist -> artist.getNationality())
        // peek() 方法能记录中间值，在调试时非常有用。
//     //XXX      .peek(nation -> System.out.println("Found nationality: " + nation))
//                .collect(Collectors.<String>toSet());
    }

    @Data
    class Artist {
        private String name;
        private String members;
        private String origin;
    }

    @Data
    class Track {
        private String name;
        private Integer length;
    }

    @Data
    class Album {
        private String name;
        private List<Track> tracks;
        private List<Artist> musicians;
    }
}
