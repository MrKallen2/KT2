import re


# проверяем ник на бота
def proverit_nik(nik):
    nik = nik.lower()
    # если есть bot или user - точно бот
    if 'bot' in nik or 'user' in nik:
        return True
    # если буквы и цифры вместе - тоже подозрительно
    if re.search(r'[a-zа-я]+\d+', nik):
        return True
    return False


# проверяем, врёт ли автор
def proverit_protivorechie(ocenka, tekst):
    tekst = tekst.lower()

    # слова-маркеры
    horoshie = ['отличн', 'супер', 'советую', 'хорош', 'класс']
    plohie = ['плох', 'ужас', 'сломал', 'фуфло', 'кошмар']

    # плохая оценка, но хвалят
    if ocenka <= 2:
        for slovo in horoshie:
            if slovo in tekst:
                return True
    # хорошая оценка, но ругают
    if ocenka >= 4:
        for slovo in plohie:
            if slovo in tekst:
                return True
    return False


# проверяем дату
def proverit_datu(data):
    # формат ГГГГ-ММ-ДД
    if not re.match(r'^\d{4}-\d{2}-\d{2}$', data):
        return False
    try:
        g, m, d = map(int, data.split('-'))
        # проверяем границы
        if not (1 <= m <= 12 and 1 <= d <= 31 and 2020 <= g <= 2026):
            return False
        return True
    except:
        return False


# читаем отзывы из файла
def chitat_otzivi():
    otzivi = []
    try:
        with open('otzivi.txt', 'r', encoding='utf-8') as f:
            for num, line in enumerate(f, 1):
                line = line.strip()
                if not line:
                    continue

                parts = line.split(';')
                if len(parts) != 5:
                    print(f'строка {num}: пропущено (не 5 полей)')
                    continue

                nik, tovar, ocenka, text, data = parts

                # пробуем превратить оценку в число
                try:
                    ocenka = int(ocenka)
                    if not (1 <= ocenka <= 5):
                        print(f'строка {num}: оценка не 1-5')
                        continue
                except:
                    print(f'строка {num}: оценка не число')
                    continue

                otzivi.append({
                    'num': num,
                    'nik': nik,
                    'tovar': tovar,
                    'ocenka': ocenka,
                    'text': text,
                    'data': data
                })
    except FileNotFoundError:
        print('файл otzivi.txt не найден!')
        return None
    except Exception as e:
        print(f'ошибка: {e}')
        return None
    return otzivi


# ищем подозрительные отзывы
def nayti_podozr(otzivi):
    podozr = []
    for o in otzivi:
        reasons = []

        if proverit_nik(o['nik']):
            reasons.append('странный ник')
        if proverit_protivorechie(o['ocenka'], o['text']):
            reasons.append('оценка не вяжется с текстом')
        if len(o['text']) < 15:
            reasons.append('слишком коротко')
        if not proverit_datu(o['data']):
            reasons.append('дата кривая')

        if reasons:
            o['reasons'] = reasons
            podozr.append(o)
    return podozr


# сохраняем отчёт
def save_report(podozr):
    print('\n' + '=' * 50)
    print('ПОДОЗРИТЕЛЬНЫЕ ОТЗЫВЫ')
    print('=' * 50)

    if not podozr:
        print('ничего подозрительного не найдено')
        return

    with open('podozritelnye.txt', 'w', encoding='utf-8') as f:
        f.write('ПОДОЗРИТЕЛЬНЫЕ ОТЗЫВЫ\n' + '=' * 40 + '\n\n')

        for o in podozr:
            print(f'строка {o["num"]}:')
            print(f'  товар: {o["tovar"]}')
            print(f'  оценка: {o["ocenka"]}')
            print(f'  текст: {o["text"][:30]}...')
            print(f'  причины: {", ".join(o["reasons"])}')
            print('  ' + '-' * 40)

            f.write(f'строка {o["num"]}: {o["nik"]}\n')
            f.write(f'  товар: {o["tovar"]}\n')
            f.write(f'  оценка: {o["ocenka"]}\n')
            f.write(f'  текст: {o["text"]}\n')
            f.write(f'  причины: {", ".join(o["reasons"])}\n')
            f.write('  ' + '-' * 40 + '\n\n')

    print(f'\nотчёт в файле podozritelnye.txt')
    print(f'всего подозрительных: {len(podozr)}')


# главная функция
def main():
    print('ДЕТЕКТОР ФАЛЬШИВЫХ ОТЗЫВОВ')
    print('версия 1.0\n')

    otzivi = chitat_otzivi()

    if otzivi is None:
        print('создай файл otzivi.txt с отзывами')
        input('нажми enter...')
        return

    if not otzivi:
        print('нет отзывов для анализа')
        input('нажми enter...')
        return

    print(f'всего отзывов: {len(otzivi)}')

    podozr = nayti_podozr(otzivi)
    save_report(podozr)

    print('\nготово!')
    input('нажми enter...')


if __name__ == '__main__':
    main()